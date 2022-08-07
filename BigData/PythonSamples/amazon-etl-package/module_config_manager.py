from collections import defaultdict

import yaml


class config_manager:

    def __init__(self):
        self._dict = defaultdict(lambda: -1, self.load_configuration())

    def load_configuration(self):
        with open(".\\config\\etl-config.yaml", "r") as file:
            try:
                return yaml.safe_load(file)
            except yaml.YAMLError as exception:
                print(exception)

    def get_value(self, config_key):
        if self._dict[config_key] == -1:
            raise Exception("Unknown configuration key : {}".format(config_key))
        return self._dict[config_key]
