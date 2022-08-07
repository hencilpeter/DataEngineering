import re
import numpy as np
from module_postal_data_provider import postal_data_provider
import pandas as pd
import traceback

class transformer:
    def __init__(self, postal_information_file):
        self.postal_data_provider = postal_data_provider(postal_information_file)

    def standardise_phone_numbers(self, data):
        return "N/A" if ("N/A" in data.upper() or len(str(data).strip()) < 8) else "+65" + str(data)[-8:]

    def get_postal_code(self, data):
        number_list = list(map(str, re.findall(r'\d+', data)))
        postal_code_list = [num for num in number_list if len(num) == 6]
        return postal_code_list[0] if len(postal_code_list) > 0 else ""

    def get_sector(self, data):
        return str(data[:2]) if len(data) > 0 else ""

    def get_district(self, data):
        return self.postal_data_provider.get_postal_district(data)

    def get_location(self, data):
        return self.postal_data_provider.get_postal_location(data)

    def get_transformed_data(self, raw_data, postal_data_provider):
        try:
            # extract and assign postal code
            raw_data["postal_code"] = raw_data["account_registration_address"].transform(self.get_postal_code)

            # assign sector
            raw_data["sector"] = raw_data["postal_code"].transform(self.get_sector)

            # assign district code
            raw_data["district"] = raw_data["sector"].transform(self.get_district)

            # assign location
            raw_data["location"] = raw_data["district"].transform(self.get_location)

            # assign the valid postal code flag
            raw_data["is_valid_postal_code"] = np.where(raw_data["district"] != "", True, False)

            # apply transformation on phone number
            raw_data["account_registration_phone_number"] = raw_data["account_registration_phone_number"].transform(
                self.standardise_phone_numbers)
            return raw_data
        except Exception as exception:
            print("Exception raised from function : get_transformed_data")
            traceback.print_exc()

