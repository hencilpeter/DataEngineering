import pandas as pd


class extractor:
    def get_source_data(self, filename, delimiter="\t"):
        return pd.read_csv(filename, delimiter=delimiter)
