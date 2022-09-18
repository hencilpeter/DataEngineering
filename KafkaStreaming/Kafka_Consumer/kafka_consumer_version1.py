import io
import json
import signal
import sys
from time import sleep
from kafka3 import KafkaConsumer

from module_constants import TableAction, MessageKey
from module_configuration import TableConfiguration
from module_record_util import MessageUtil

shutting_down = False


def process_messages():
    pass


def safe_exit(*args, **kwargs):
    print("exit safely called. going to process the pending messages if any...")
    process_messages()
    print("exiting safely...")
    exit()


def safe_deserialize(value):
    if value is None:
        value = '{"id":"00", "payload": "Dummy"}'
    return json.loads(value)


if __name__ == "__main__":
    # test area - start
    #print(TableAction.UPDATE)
    #print(TableConfiguration.get_configuration())
    #exit()
    # test area - end

    signal.signal(signal.SIGINT, safe_exit)
    signal.signal(signal.SIGTERM, safe_exit)

    print("consumer main called...")
    print("loading table configuration...")
    database_name ="sqlserver"
    table_schema = "dbo"
    table_name = "customer"
    config_tables = TableConfiguration.get_configuration()
    consumer = KafkaConsumer(bootstrap_servers=["localhost:9092"],
                             group_id='my-group',
                             # consumer_timeout_ms=60000,
                             auto_offset_reset='latest',  # auto_offset_reset='earliest',
                             enable_auto_commit=True,
                             value_deserializer=lambda m: safe_deserialize(m))
    Util = MessageUtil()

    consumer.subscribe("sqlserver.dbo.customer")
    print("consumer is listening....")
    for message in consumer:
        message = message.value

        if message[MessageKey.PAYLOAD] == "Dummy":
            # skip the dummy message
            continue

        print("****begin message block****")
        payLoad = message[MessageKey.PAYLOAD]
        # print("action : {}".format(Util.get_action_name(payLoad)))
        # print("source  ts_ms : {}".format(Util.get_record_source_timestamp(payLoad)))
        # print("payload ts_ms : {}".format(Util.get_record_kafka_timestamp(payLoad)))
        list_column_order = ['name', 'id']
        print("writable record : {}".format(Util.get_writable_record(payLoad, list_column_order)))
        print("****end message block****")
