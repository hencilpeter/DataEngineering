import os
import threading
import time

from src.thread.thread_base import ThreadBase
from src.util.custom_logger import CustomLogger


class ThreadlifeNotifier(ThreadBase, threading.Thread):
    def __init__(self, thread_name, application_ticket_id_file, rip_request_file, application_terminate_event,
                 sleep_interval):
        super(ThreadlifeNotifier, self).__init__(thread_name=thread_name,
                                                 application_terminate_event=application_terminate_event,
                                                 sleep_interval=sleep_interval)

        self.application_ticket_id_file = application_ticket_id_file
        self.rip_request_file = rip_request_file

    def run(self):
        CustomLogger.log_print_info("thread life notifier thread with name : {} started...".format(self.thread_name))
        while not self.application_terminate_event.isSet():
            if os.path.isfile(self.rip_request_file):
                CustomLogger.log_print_info("consumer application terminate request received...")
                self.application_terminate_event.set()
                os.remove(self.rip_request_file)
                if os.path.isfile(self.application_ticket_id_file):
                    os.remove(self.application_ticket_id_file)
            time.sleep(self.sleep_interval)

        CustomLogger.log_print_info("thread life notifier thread with name : {} terminates...".format(self.thread_name))
