import datetime
import json
import threading
import time

# from kafka import KafkaConsumer
from src.data.application_configuration_name import ApplicationConfigurationName

from src.data.application_configuration_value import ApplicationConfigurationValue
from src.data.payload import Payload
from src.data.payload_tag_name import PayloadTagName
from src.data.target_record import TargetRecord
from src.thread.thread_base import ThreadBase
from src.util.custom_logger import CustomLogger
from src.util.reader.config_reader import ConfigReader
from src.util.writer.hdfs_writer import HdfsWriter


class MessageReceiver(ThreadBase, threading.Thread):
    def __init__(self, thread_name, application_terminate_event, sleep_interval):
        super(MessageReceiver, self).__init__(thread_name=thread_name,
                                              application_terminate_event=application_terminate_event,
                                              sleep_interval=sleep_interval)
        self.running_mode = ConfigReader.get_application_config(ApplicationConfigurationName.COMMON_SECTION,
                                                                ApplicationConfigurationName.RUNNING_MODE)
        self.kafka_bootstrap_server = ConfigReader.get_application_config(ApplicationConfigurationName.KAFKA_SECTION,
                                                                          ApplicationConfigurationName.BOOTSTRAP_SERVER)
        self.kafka_topic_name = ConfigReader.get_application_config(ApplicationConfigurationName.KAFKA_SECTION,
                                                                    ApplicationConfigurationName.TOPIC_NAME)
        self.kafka_auto_offset_reset_value = ConfigReader.get_application_config(
            ApplicationConfigurationName.KAFKA_SECTION,
            ApplicationConfigurationName.AUTO_OFFSET_RESET)
        self.enable_auto_commit_flag = ConfigReader.get_application_config(ApplicationConfigurationName.KAFKA_SECTION,
                                                                           ApplicationConfigurationName.ENABLE_AUTO_COMMIT)

    def run(self):
        CustomLogger.log_print_info("message receiver thread with name {} started...".format(self.get_thread_name()))
        hdfs_writer = HdfsWriter()

        if self.running_mode == ApplicationConfigurationValue.RUNNING_MODE_DEBUG:
            print("debugging mode...")
            with open(
                    "C:\\Users\\User\\Documents\\GitHub\\DataEngineering\\python\\data_streaming\\src\\data\\test_message.txt") as file:
                # data = file.read()
                # dict_data = json.loads(data)
                dict_data = json.load(file)
                dict_message = dict_data['message']
                message_table_name = Payload(dict_message=dict_message).get_value_from_payload_tag(
                    PayloadTagName.TABLE_NAME)
                if ConfigReader.get_tables_config_using_single_key(message_table_name) == -1:
                    CustomLogger.log_print_warning(
                        "table name {} is not configured. so current message is skipped.".format(message_table_name))
                target_record = TargetRecord(dict_message=dict_message)
                # TargetRecordSpecialFieldName.KAKFA_TOPIC
                # TargetRecordspecialFieldName.KAFKA_TOPIC_OFFSET
                target_record.set_dict_value(kakfa_topic="test_topic", kafka_topic_offset="test_offset")
                print ("target record timestamp : {} target record : {}".format(datetime.datetime.now(),
                                                                                target_record.get_target_record()))
                # target_path = CommonUtil.get_data_folder_path(target_record.get_table_name().
                #
                target_record.get_timestamp()
                hdfs_writer.write_data(target_record=target_record)
            self.application_terminate_event.set()
            exit(0)

        kafka_consumer = None
        try:
            # kafka_consumer = KafkaConsumer(
            # bootstrap_servers=[self.kafka_bootstrap_server], # kafka broker
            # group_id='my-group',
            # auto_offset_reset = self.kafka_auto_offset_reset_value,
            #                     enable_auto_commit = self.enable_auto_commit_flag
            # # valve_deserializer=lambda m: safe_deserialize(m)
            # )
            # TODO - enable the commented code
            pass

        except Exception as ex:
            CustomLogger.log_print_error("exception while creating kafka consumer : {}".format(str(ex)))
            self.application_terminate_event.set()
            exit(1)
            try:
                kafka_consumer.subscribe(self.kafka_topic_name)
            except Exception as ex:
                CustomLogger.log_print_error("exception while subscribing to the kafka topic : {}".format(str(ex)))
                self.application_terminate_event.set()
                exit(1)

        CustomLogger.log_print_info("kafka consumer is ready to consume the messages.")
        while not self.get_application_terminate_event().isset():
            for message in kafka_consumer:
                # message message.value
                print("new message timestamp : {}".format(datetime.datetime.now()))
                temp_dict = json.loads(message.value)
                print("received message : {}".format(temp_dict['message']))
                dict_message = temp_dict['message']
                message_table_name = Payload(dict_message=dict_message).get_value_from_payload_tag(
                    PayloadTagName.TABLE_NAHE)
                if ConfigReader.get_tables_config_using_single_key(message_table_name) == -1:
                    CustomLogger.Log_print_warning(
                        "table name {} is not configured. so current message is skipped.".format(message_table_name))
                    continue

                target_record = TargetRecord(dict_message=dict_message)

                target_record.set_dict_value(kakfa_topic=message.topic, kafka_topic_offset=message.offset)

                print("target record timestamp : {}. target record : {}".format(datetime.datetime.now(),
                                                                                target_record.get_target_record()))

                hdfs_writer.write_data(target_record=target_record)

                # while not self.get_application_terminate_event().isset():
                # TODO:
                # 1. read from kafka topic
                # 2. apply simple changes on message
                # 3. append the message in message catalog
                # 4. append the newly received message detail in log DB
                # if self.get_application_terminate_event().isset():

                CustomLogger.log_print_info(
                    "message receiver thread with name : {} is active...".format(self.get_thread_name()))
                time.sleep(self.get_sleep_interval())
            CustomLogger.log_print_info(
                "message receiver thread with name :{} terminates...".format(self.get_thread_name()))
