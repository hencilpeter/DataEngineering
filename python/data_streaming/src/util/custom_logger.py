import logging

from src.util.common_util import CommonUtil


class CustomLogger:
    @staticmethod
    def initialize():
        # logging.basicConfig(filename=CommonUtil.get_log_filename(),
        #                     filemode='a',
        #                     #format='%(asctime)s,%(msecs)d %(name)s %(levelname)s %(message)s',
        #                     #format='%(asctime)s : %(msecs)d-%(name)s %(levelname)s %(message)s',
        #                     format='%(asctime)s %(msecs)d-%(name)s %(levelname)s %(message)s',
        #                     datefmt='%Y/%m/%d %H:%M:%s', level=logging.DEBUG)
        logging.basicConfig(filename=CommonUtil.get_log_filename(),
                            filemode='a',
                            format='%(asctime)s.%(msecs)03d %(levelname)s {%(module)s} [%(funcName)s] %(message)s',
                            datefmt='%Y-%m-%d,%H:%M:%S', level=logging.INFO)
        print("log file {} created...".format(CommonUtil.get_log_filename()))

    @staticmethod
    def log_info(message):
        logging.info(message)

    @staticmethod
    def log_warning(message):
        logging.warning(message)

    @staticmethod
    def log_error(message):
        logging.error(message)

    @staticmethod
    def log_print_info(message):
        CustomLogger.log_info(message)
        print("INFO  : " + message)

    @staticmethod
    def log_print_warning(message):
        CustomLogger.log_warning(message)
        print("WARN : " + message)

    @staticmethod
    def log_print_error(message):
        CustomLogger.log_error(message)
        print("ERROR : " + message)
