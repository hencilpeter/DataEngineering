import threading
import time
from collections import defaultdict

from src.data.thread_configuration_name import ThreadConfigurationName
from src.thread.thread_base import ThreadBase
from src.thread.thread_factory import ThreadFactory
from src.util.custom_logger import CustomLogger
from src.util.reader.config_reader import ConfigReader


class ThreadManager(ThreadBase, threading.Thread):
    def __init__(self):
        # initialize thread life manager
        sleep_interval_in_seconds = ConfigReader.get_thread_config(
            ThreadConfigurationName.THREAD_MANAGER,
            ThreadConfigurationName.SLEEP_INTERVAL_IN_SECONDS)
        application_terminate_event = threading.Event()
        super(ThreadManager, self).__init__(thread_name=ThreadConfigurationName.THREAD_MANAGER,
                                            application_terminate_event=application_terminate_event,
                                            sleep_interval=sleep_interval_in_seconds)

        self.thread_dictionary = defaultdict(lambda: -1)
        # message receiver
        self.thread_dictionary[ThreadConfigurationName.MESSAGE_RECEIVER] = ThreadFactory.create_thread_object(
            ThreadConfigurationName.MESSAGE_RECEIVER,
            application_terminate_event=application_terminate_event)
        # thread life notifier
        self.thread_dictionary[
            ThreadConfigurationName.THREAD_LIFE_NOTIFIER_THREAD_NAME] = ThreadFactory.create_thread_object(
            ThreadConfigurationName.THREAD_LIFE_NOTIFIER_THREAD_NAME,
            application_terminate_event=application_terminate_event)

        # # initialize garbage collector
        # self.thread_dictionary[
        #     ThreadConfigurationName.GARBAGE_COLLECTOR_THREAD_NAME] = ThreadFactory.create_thread_object(
        # ThreadConfigurationName.GARBAGE_COLLECTOR_THREAD_NAME,
        # application_terminate_event=application_terminate_event)

        # worker transaction data processor
        # ThreadConfigurationName.GARBAGE_COLLECTOR_THREAD_NAME] = ThreadFactory.create_thread_object(
        #     ThreadConfigurationName.GARBAGE_COLLECTOR_THREAD_NAME,
        #     application_terminate_event = application_terminate_event)
        #
        # # # worker transaction data processor
        # self.transaction_data_processor_count = ConfigReader.get
        # thread_config(
        #     # #
        #     ThreadConfigurationName.TRANSACTION_DATA_PROCESSOR,
        #     ThreadConfigurationName.THREAD_COUNT)
        # for thread_number in range(self.transaction_data_processor_count):
        # # # # #
        #     transaction_thread_full_name = ThreadConfigurationName.TRANSACTION_DATA_PROCESSOR + "_" + str(
        #         I
        # thread_number + 1)
        # self.thread_dictionary[transaction_thread_full_name] = ThreadFactory.create_
        # thread_object(
        #     thread_name=ThreadConfigurationName.TRANSACTION_DATA_PROCESSOR
        # application_terminate_event = application_terminate_event, thread_number = thread_number + 1)

    def run(self):
        CustomLogger.log_print_info("thread manager started...")
        # 1. start thread life notifier
        self.thread_dictionary[ThreadConfigurationName.THREAD_LIFE_NOTIFIER_THREAD_NAME].start()

        # # 2. start garbage collector thread
        # self.thread_dictionary[ThreadConfigurationName.GARBAGE_COLLECTOR_THREAD_NAME].start()
        #
        # # 3. start worker transaction data processor
        # for worker_thread_number in range(self.transaction_data_processor_count):
        # worker_thread_full_name 3 ThreadConfigurationName TRANSACTION_ DATA_ PROCESSOR +
        # + str(
        # worker_thread_number + 1)
        # #
        # self.thread_dictionary[worker_thread_full_name].start()

        # 4. start message receiver
        self.thread_dictionary[ThreadConfigurationName.MESSAGE_RECEIVER].start()
        while not self.application_terminate_event.isSet():
            time.sleep(self.get_sleep_interval())

        if self.application_terminate_event.isSet():
            CustomLogger.log_print_warning(
                "thread manager received the application termination event. thread manager will wait until all the threads get terminated..")
            self.wait_until_thread_alive(self.thread_dictionary[ThreadConfigurationName.MESSAGE_RECEIVER])

            # # self.wait_until_thread_alive(
            # #
            # self. thread_dictionary[ThreadConfigurationName.THREAD_LIFE_NOTIFIER_THREAD_NAME])
            # # self.wait_until_thread_alive(
            # #
            # self.thread_dictionary[ThreadConfigurationName.GARBAGE_COLLECTOR_THREAD_NAME])
            # # for worker_thread_number in range(self.transaction_data_processor_count):
            # #
            # worker thread _full_name = ThreadConfigurationName.TRANSACTION_DATA_PROCESSOR + "" + str(
            # # #
            # worker_thread_number + 1)
            # self.wait_until_thread_alive(self.thread_dictionary[worker_thread_full_name])
            CustomLogger.log_print_info("thread manager waited and confirmed that other threads terminated safely...")
        else:
            CustomLogger.log_print_warning("thread manager terminates abnormally.")
        CustomLogger.log_print_warning("thread manager terminates now..")

    def wait_until_thread_alive(self, thread_object):
        while thread_object.is_alive():
            CustomLogger.log_print_warning(
                "thread with name :{} is active. thread manager is waiting until the normal termination".format(
                    thread_object.get_thread_name()))
            time.sleep(self.get_sleep_interval())
