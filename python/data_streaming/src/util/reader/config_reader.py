import json
from collections import defaultdict

from src.data.application_configuration_name import ApplicationConfigurationName


# import yaml
# from yaml.loader import Safeloader


class ConfigReader:
    dict_application_configuration = defaultdict(lambda: -1)
    dict_thread_configuration = defaultdict(lambda: -1)
    dict_tables_configuration = defaultdict(lambda: -1)

    @staticmethod
    def load_configuration(application_config_filename):
        with open(application_config_filename) as file:
            ConfigReader.dict_application_configuration.update(json.load(file))
            # ConfigReader.dict_application_configuration.update(yaml.load(file, Loader=Safeloader))

        if len(ConfigReader.dict_application_configuration) == 0:
            print ("application configuration is emtpy. application exit!")
            exit(0)

        thread_config_filename = ConfigReader.get_application_config(ApplicationConfigurationName.COMMON_SECTION,
                                                                     ApplicationConfigurationName.THREAD_CONFIG_FILE_NAME)

        with open(thread_config_filename) as file:
            ConfigReader.dict_thread_configuration.update(json.load(file))
            # ConfigReader.dict_thread_configuration.update(yaml.load(file, Loader=SafeLoader)

        if len(ConfigReader.dict_thread_configuration) == 0:
            print("thread configuration is emtpy. application exit!")
            exit(0)

        tables_config_filename = ConfigReader.get_application_config(ApplicationConfigurationName.COMMON_SECTION,
                                                                     ApplicationConfigurationName.TABLES_CONFIG_FILE_NAME)

        with open(tables_config_filename) as file:
            ConfigReader.dict_tables_configuration.update(json.load(file))
            # ConfigReader.dict_tables_configuration.update(yaml.load(file, Loader=Safeloader))

        if len(ConfigReader.dict_tables_configuration) == 0:
            print ("tables configuration is emtpy. application exit!")
            exit(0)

    @staticmethod
    def get_application_config_using_single_key(config_name):
        return ConfigReader.dict_application_configuration[config_name]

    @staticmethod
    def get_application_config(parent_config_name, child_config_name):
        return ConfigReader.dict_application_configuration[parent_config_name][child_config_name]

    @staticmethod
    def get_thread_config_using_single_key(config_name):
        return ConfigReader.dict_thread_configuration[config_name]

    @staticmethod
    def get_thread_config(parent_config_name, child_config_name):
        return ConfigReader.dict_thread_configuration[parent_config_name][child_config_name]

    @staticmethod
    def get_tables_config_using_single_key(config_name):
        return ConfigReader.dict_tables_configuration[config_name]

    @staticmethod
    def get_tables_config(parent_config_name, child_config_name):
        return ConfigReader.dict_tables_configuration[parent_config_name][child_config_name]
