import pandas as pd
from collections import defaultdict

class district_location:
    def __init__(self, district, location):
        self._district = district
        self._location = location

    def get_district(self):
        return self._district

    def get_location(self):
        return self._location

class postal_data_provider:
    def __init__(self, postal_data_file):
        self._postal_information_dict = defaultdict(lambda: -1, self.load_postal_information(postal_data_file))

    def load_postal_information(self, postal_data_file):
        try:
            postal_information_data = pd.read_csv(postal_data_file)
            dict_temp = dict()
            for index, row in postal_information_data.iterrows():
                for sector in str(row['postal_sector']).split(","):
                    dict_temp[str(sector).strip()]  = district_location('{:02}'.format(int(str(row["postal_district"]).strip())), str(row['general_location']).strip())
            return dict_temp
        except Exception as exception:
            print("Exception raised from function : load_postal_information")
            traceback.print_exc()

    def get_postal_district(self, postal_sector):
        if self._postal_information_dict[postal_sector] == -1:
            #raise Exception("Unknown postal sector for postal district: {}".format(postal_district))
            return ""
        else:
            return self._postal_information_dict[postal_sector].get_district()

    def get_postal_location(self, postal_sector):
        if self._postal_information_dict[postal_sector] == -1:
            #raise Exception("Unknown postal sector for postal location: {}".format(postal_district))
            return ""
        else:
            return self._postal_information_dict[postal_sector].get_location()

