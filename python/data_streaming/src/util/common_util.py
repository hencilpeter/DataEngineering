import os
from datetime import datetime

from src.data.application_configuration_name import ApplicationConfigurationName
from src.data.table_configuration_name import TableConfigurationName
from src.data.table_configuration_value import TableConfigurationValue
from src.util.reader.config_reader import ConfigReader


class CommonUtil:
    application_name = application_ticket_id = heartbeat_folder_path = heartbeat_file_extension = rip_request_file_name = ""
    log_file_base_folder = ""

    @staticmethod
    def initialize():
        CommonUtil.application_name = ConfigReader.get_application_config(ApplicationConfigurationName.COMMON_SECTION,
                                                                          ApplicationConfigurationName.APPLICATION_NAME)
        CommonUtil.application_ticket_id = os.path.join(
            CommonUtil.application_name + "_" + datetime.now().strftime("%Y%m%d_%I%M%S"))
        CommonUtil.heartbeat_folder_path = ConfigReader.get_application_config(
            ApplicationConfigurationName.COMMON_SECTION, ApplicationConfigurationName.HEARTBEAT_FOLDER_PATH)
        CommonUtil.heartbeat_file_extension = ConfigReader.get_application_config(
            ApplicationConfigurationName.COMMON_SECTION,
            ApplicationConfigurationName.APPLICATION_TICKET_ID_FILE_EXTENSION)
        CommonUtil.application_ticket_id_filename = CommonUtil.heartbeat_folder_path + CommonUtil.application_ticket_id + CommonUtil.heartbeat_file_extension
        CommonUtil.rip_request_file_name = ConfigReader.get_application_config(
            ApplicationConfigurationName.COMMON_SECTION,
            ApplicationConfigurationName.RIP_REQUEST_FOLDER_PATH) + CommonUtil.application_ticket_id + CommonUtil.heartbeat_file_extension

        CommonUtil.log_file_base_folder = ConfigReader.get_application_config(
            ApplicationConfigurationName.COMMON_SECTION,
            ApplicationConfigurationName.LOG_PATH)

    @staticmethod
    def get_application_name():
        return CommonUtil.application_name

    @staticmethod
    def get_application_ticket_id():
        return CommonUtil.application_ticket_id

    @staticmethod
    def get_application_ticket_id_filename():
        return CommonUtil.application_ticket_id_filename

    @staticmethod
    def get_heartbeat_folder_path():
        return CommonUtil.heartbeat_folder_path

    @staticmethod
    def get_rip_request_filename():
        return CommonUtil.rip_request_file_name

    # Log
    @staticmethod
    def get_log_filename():
        return CommonUtil.log_file_base_folder + CommonUtil.application_ticket_id + ".log"

    # hdfs
    @staticmethod
    def get_data_folder_path(table_name, timestamp):
        timestamp = timestamp.replace("T", " ")
        datetime_value = datetime.strptime(timestamp, '%Y-%m-%d %H:%M:%S.%f')
        hdfs_full_path = ConfigReader.get_tables_config(table_name, TableConfigurationName.HDFS_DATA_PATH)
        partition_frequency = ConfigReader.get_tables_config(table_name,
                                                             TableConfigurationName.PARTITION_FREQUENCY)

        if partition_frequency == TableConfigurationValue.PARTITION_FREQUENCY_DAILY:
            hdfs_full_path = os.path.join(hdfs_full_path, table_name, datetime_value.date().strftime("%v%n%d"))
        elif partition_frequency == TableConfigurationValue.PARTITION_FREQUENCY_HOUR:
            hdfs_full_path = os.path.join(hdfs_full_path, table_name, datetime_value.date().strftime("%Y%m%d"),
                                          str(datetime_value.hour))
        elif partition_frequency == TableConfigurationValue.PARTITION_FREQUENCY_MINUTE:
            hdfs_full_path = os.path.join(hdfs_full_path, table_name, datetime_value.date().strftime("%Y%m%d"),
                                          str(datetime_value.hour), str(datetime_value.minute))

        return hdfs_full_path
