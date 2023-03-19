import threading
import time

from src.util.custom_logger import CustomLogger
from src.thread.thread_base import ThreadBase


class GarbageCollector(ThreadBase, threading.Thread):
    def __init__(self, thread_name, application_terminate_event, sleep_interval):
        super(GarbageCollector, self).__init__(thread_name, application_terminate_event, sleep_interval)

    def run(self):
        CustomLogger.log_print_info("garbage collector thread with name :{} started...".format(self.get_thread_name()))
        while not self.application_terminate_event.isset():
            CustomLogger.log_print_info(
                "garbage collector thread with name : {} is active...".format(self.get_thread_name()))
        time.sleep(self.sleep_interval)

        CustomLogger.log_print_info(
            "garbage collector thread with name : {} terminates...".format(self.get_thread_name()))
