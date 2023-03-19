from src.data.thread_configuration_name import ThreadConfigurationName
from src.thread.garbage_collector import GarbageCollector
from src.thread.message_receiver import MessageReceiver
from src.thread.thread_life_notifier import ThreadlifeNotifier
from src.thread.transaction_data_processor import TransactionDataProcessor
from src.util.common_util import CommonUtil
from src.util.custom_logger import CustomLogger
from src.util.reader.config_reader import ConfigReader


class ThreadFactory:
    @staticmethod
    def create_thread_object(thread_name, application_terminate_event, thread_number=0):
        thread_object = ""
        application_ticket_id_file = CommonUtil.get_application_ticket_id_filename()
        sleep_interval = ConfigReader.get_thread_config(thread_name, ThreadConfigurationName.SLEEP_INTERVAL_IN_SECONDS)
        if thread_name == ThreadConfigurationName.THREAD_LIFE_NOTIFIER_THREAD_NAME:
            rip_request_file = CommonUtil.get_rip_request_filename()
            thread_object = ThreadlifeNotifier(thread_name=thread_name,
                                               application_ticket_id_file=application_ticket_id_file,
                                               rip_request_file=rip_request_file,
                                               application_terminate_event=application_terminate_event,
                                               sleep_interval=sleep_interval)
        elif thread_name == ThreadConfigurationName.GARBAGE_COLLECTOR_THREAD_NAME:
            thread_object = GarbageCollector(thread_name=thread_name,
                                             application_terminate_event=application_terminate_event,
                                             sleep_interval=sleep_interval)
        elif thread_name == ThreadConfigurationName.MESSAGE_RECEIVER:
            thread_object = MessageReceiver(thread_name=thread_name,
                                            application_terminate_event=application_terminate_event,
                                            sleep_interval=sleep_interval)

        elif thread_name == ThreadConfigurationName.TRANSACTION_DATA_PROCESSOR:
            thread_full_name = thread_name + "_" + str(thread_number)
            table_list = ConfigReader.get_thread_config(ThreadConfigurationName.TRANSACTION_DATA_PROCESSOR,
                                                        ThreadConfigurationName.TABLE_LIST)

            worker_thread_count = ConfigReader.get_thread_config(ThreadConfigurationName.TRANSACTION_DATA_PROCESSOR,
                                                                 ThreadConfigurationName.THREAD_COUNT)

            table_groups = ThreadFactory.get_split_numbers_into_groups(range(len(table_list)), worker_thread_count)
            number_range = [table_groups[thread_number - 1]]
            table_list_subset = table_list[number_range[0]:number_range[-1] + 1]
            thread_object = TransactionDataProcessor(thread_name=thread_full_name,
                                                     table_list=table_list_subset,
                                                     sleep_interval=sleep_interval,
                                                     application_terminate_event=application_terminate_event)
        else:
            CustomLogger.log_error("Unknown thread name : {} application exits.".format(thread_name))
            exit(0)

        return thread_object

    @staticmethod
    def get_split_numbers_into_groups(range_numbers, number_of_groups):
        avg_count = len(range_numbers) / float(number_of_groups)
        result = []
        processed_numbers_count = 0.0
        while processed_numbers_count < len(range_numbers):
            result.append(range_numbers[int(processed_numbers_count): int(processed_numbers_count + avg_count)])
            processed_numbers_count += avg_count

        return result
