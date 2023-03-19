from src.util.custom_logger import CustomLogger
from src.util.common_util import CommonUtil
from src.util.file_util import FileUtil
from src.util.reader.config_reader import ConfigReader


class AppInitializer:
    @staticmethod
    def initialize(application_config_filename):
        print("application configuration file name : {}".format(application_config_filename))
        ConfigReader.load_configuration(application_config_filename)
        AppInitializer.initialize_static_members()

    @staticmethod
    def initialize_static_members():
        CommonUtil.initialize()
        CustomLogger.initialize()
        pass

    @staticmethod
    def create_application_ticket_id_file():
        ticket_id_filename = CommonUtil.get_application_ticket_id_filename()
        FileUtil().create_empty_file(ticket_id_filename)
        CustomLogger.log_print_info("application ticket id file {} created.".format(ticket_id_filename))
        pass
