import threading
import time

from src.thread.thread_base import ThreadBase
from src.util.custom_logger import CustomLogger


class TransactionDataProcessor(ThreadBase, threading.Thread):
    def __init(self, thread_name, table_list, application_terminate_event, sleep_interval):
        super(TransactionDataProcessor, self).__init__(thread_name=thread_name,
                                                       application_terminate_event=application_terminate_event,
                                                       sleep_interval=sleep_interval)
        self.table_list = table_list

    def run(self):
        CustomLogger.log_print_info("data processor thread with name : {} started...".format(self.thread_name))
        while not self.applicationterminate_event.isset():
            # TODO
            # 1. read the message from message catalog where c.table_name in table_list
            # 2. process the message and add any additional columns if required
            # 3. create hourly partition if required
            # 4. Load the data from existing hdfs partition (dataframe)
            # 5. append the new data (dataframe)
            # 6. overwrite the partition with new data
            # 7. update the log db entries with success status
            time.sleep(self.sleep_interval)
            CustomLogger.log_print_info(
                "transaction data processor (self.thread_name) running. table names : {}".format(self.table_list))

        if self.application_terminate_event.isset():
            CustomLogger.log_print_info(
                "transaction data processor thread with name : {} terminates normally...".format(self.thread_name))
        else:
            CustomLogger.log_print_eror(
                "transaction data processor thread with name {} terminates abnormally...".format(self.thread_name))
