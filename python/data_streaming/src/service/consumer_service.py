from src.app.app_initializer import AppInitializer
from src.util.custom_logger import CustomLogger
from src.thread.thread_manager import ThreadManager
from src.util.common_util import CommonUtil


class ConsumerService:
    def __init__(self, application_config_filename):
        # initialize consumer
        print("message consumer initialization started.")
        AppInitializer.initialize(application_config_filename=application_config_filename)
        AppInitializer.create_application_ticket_id_file()
        self.thread_manager = ThreadManager()
        print ("message consumer initialization completed.")

    def start_consumer(self):
        try:
            CustomLogger.log_print_info("consumer application {} started.".format(CommonUtil.application_name))
            self.thread_manager.start()
            self.thread_manager.join()
            CustomLogger.log_print_info("consumer application {} terminates.".format(CommonUtil.application_name))
        except Exception as ex:
            CustomLogger.log_print_error("exception {} raised from start_consumer function.".format(str(ex)))
