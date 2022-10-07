import io
import json
import signal
import sys
import threading
from queue import Queue, Empty
from time import sleep

from kafka3 import KafkaConsumer
from module_configuration import TableConfiguration
from module_constants import TableAction, MessageKey
from module_record_util import MessageUtil
from pyspark.sql import SparkSession
from datetime import datetime

shutting_down = False
message_queue = Queue()
Util = MessageUtil()
sparkSession = SparkSession.builder.master("local[1]").appName("SparkApp").getOrCreate()

table_name = "trade"


def safe_exit(*args, **kwargs):
    print("safe exit called. going to process the pending messages if any...")
    global shutting_down
    shutting_down = True
    process_messages()
    print("exiting safely...")
    exit()


def safe_deserialize(value):
    if value is None:
        value = '{"id":"00", "payload": "Dummy"}'
    return json.loads(value)


class MessageConsumer(threading.Thread):
    def __init__(self, table_configuration, table_name):
        threading.Thread.__init__(self)
        self.table_configuration = table_configuration
        self.table_name = table_name

    def run(self):
        kafka_consumer = KafkaConsumer(
            bootstrap_servers=[table_config.get_configuration_value('bootstrap_servers', self.table_name)],
            group_id='my-group',
            #auto_offset_reset='latest',  # auto_offset_reset='earliest',
            auto_offset_reset='earliest',
            enable_auto_commit=True,
            value_deserializer=lambda m: safe_deserialize(m))

        topic_name = self.table_configuration.get_configuration_value('server_name',
                                                                      table_name) + "." + self.table_configuration.get_configuration_value(
            'table_schema', table_name) + "." + table_name
        print("listening to the topic : {}".format(topic_name))
        # kafka_consumer.subscribe("sqlserver.dbo.customer")
        kafka_consumer.subscribe(topic_name)

        for message in kafka_consumer:
            print("new message received...")
            message = message.value

            if message[MessageKey.PAYLOAD] == "Dummy":
                continue  # skip the dummy message

            message_queue.put(message)

            if shutting_down:
                break

        consumer.close()


def process_messages(table_config, table_name):
    print("processing message in queue buffer")
    message_buffer = []
    try:
        while message_queue.qsize() > 0:
            print("Queue Length : {}".format(message_queue.qsize()))
            message = message_queue.get_nowait()
            payLoad = message[MessageKey.PAYLOAD]
            list_column_order = str(table_config.get_configuration_value('columns', table_name)).split(',')
            message_buffer.append(Util.get_writable_record(payLoad, list_column_order))
    except Exception as exp:
        print("Exception raised from process_message function. exception : ".format(exp))

    # save the messages in HDFS
    print("buffer size : {}".format(len(message_buffer)))
    print(message_buffer)

    # write to HDFS
    if len(message_buffer) > 0:
        df = sparkSession.createDataFrame(message_buffer)
        write_mode = table_config.get_configuration_value('hdfs_write_mode', table_name)
        write_format = table_config.get_configuration_value('hdfs_write_format', table_name)
        name_node = table_config.get_configuration_value('hadoop_namenode', table_name)
        hdfs_path = table_config.get_configuration_value('hdfs_data_path', table_name)
        business_date = datetime.today().strftime('%Y%m%d')
        # df.coalesce(1).write.format("csv").option("header", "false").mode('append').save('hdfs://localhost:9000/data/')
        df.coalesce(1).write.format(write_format).option("header", "false").mode(write_mode).save(
            name_node + "/" + hdfs_path + "/" + table_name + "/" + business_date)


if __name__ == "__main__":
    # test area - start
    # print(TableAction.UPDATE)
    # print(TableConfiguration.get_configuration())
    # table_config = TableConfiguration()
    # print(table_config.get_configuration_value('server_name', 'customer') )
    # exit()
    # test area - end

    signal.signal(signal.SIGINT, safe_exit)
    signal.signal(signal.SIGTERM, safe_exit)

    print("consumer main called...")
    print("loading table configuration...")

    # create table configuration object
    table_config = TableConfiguration()

    # start the message consumer
    message_consumer = MessageConsumer(table_config, table_name)
    message_consumer.daemon = True
    message_consumer.start()

    while True:
        process_messages(table_config, table_name)
        sleep(table_config.get_configuration_value('write_frequency_in_seconds', table_name))
