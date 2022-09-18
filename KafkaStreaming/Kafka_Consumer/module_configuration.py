from module_constants import Configuration as Config
import yaml2
import os


class TableConfiguration:
    def __init__(self):
        self.configuration = self.read_configuration()

    def read_configuration(self):
        config_file = os.path.join(Config.FILEPATH, Config.FILENAME)
        print("reading configuration file : {} ".format(config_file))
        with open(config_file, "r") as file:
            try:
                dict_configuration = yaml2.safe_load(file)
                return dict_configuration
            except yaml2.YAMLError as exception:
                print(exception)

    def get_configuration_value(self, config_key, table_name):
        if len(config_key) == 0 or len(table_name) == 0:
            return ""

        if table_name in self.configuration and config_key in self.configuration[table_name]:
            return self.configuration[table_name][config_key]
        elif config_key in self.configuration["default"]:
            return self.configuration["default"][config_key]
        else:
            return ""
