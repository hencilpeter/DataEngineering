import datetime
import os
import sys
import pandas as pd

sys.path.append(os.path.dirname(os.path.dirname(os.path.realpath(__file__))))

from reader.configuration_reader import ConfigurationCatalog


class DataFileWriter:
    def __init__(self):
        pass

    def get_filename_with_timestamp(self, filename):
        file_extension = filename[::-1][:filename[::-1].find(".")][::-1]
        filename_without_extension = filename[:len(filename) - len(file_extension) - 1]
        current_timestamp = datetime.datetime.now()
        formatted_timestamp = str(current_timestamp).replace(" ", "_").replace(".", "_").replace(":", "-")
        out_filename = filename_without_extension + "_" + formatted_timestamp + "." + file_extension
        return out_filename

    def write_excel_data(self, df_result, file_name, sheet_name, mode="w"):
        with pd.ExcelWriter(file_name, engine="openpyxl", mode=mode) as writer:
            df_result.to_excel(writer, sheet_name=sheet_name, index=False)

    def write_result_data(self, configuration_manager, df_valid_case_values, df_lack_of_case_values,
                          df_reroute_cases_sheet, df_no_coverage_cases_values, df_duplicate_cases_values):
        outfile_name = configuration_manager.get_common_config(nested_config_name=ConfigurationCatalog.outfile_name)
        final_out_filename = self.get_filename_with_timestamp(outfile_name)
        outfile_data_invalid_sheet_name = configuration_manager. \
            get_common_config(nested_config_name=ConfigurationCatalog.outfile_data_invalid_sheet_name)
        outfile_data_valid_sheet_name = configuration_manager. \
            get_common_config(nested_config_name=ConfigurationCatalog.outfile_data_valid_sheet_name)
        outfile_data_reroute_sheet_name = configuration_manager. \
            get_common_config(nested_config_name=ConfigurationCatalog.outfile_data_reroute_sheet_name)
        outfile_data_no_coverage_sheet_name = configuration_manager. \
            get_common_config(nested_config_name=ConfigurationCatalog.outfile_data_no_coverage_sheet_name)
        outfile_data_duplicate_sheet_name = configuration_manager. \
            get_common_config(nested_config_name=ConfigurationCatalog.outfile_data_duplicate_sheet_name)

        self.write_excel_data(df_lack_of_case_values, final_out_filename, outfile_data_invalid_sheet_name)
        self.write_excel_data(df_valid_case_values, final_out_filename, outfile_data_valid_sheet_name, "a")
        self.write_excel_data(df_reroute_cases_sheet, final_out_filename, outfile_data_reroute_sheet_name, "a")
        self.write_excel_data(df_no_coverage_cases_values, final_out_filename, outfile_data_no_coverage_sheet_name, "a")
        self.write_excel_data(df_duplicate_cases_values, final_out_filename, outfile_data_duplicate_sheet_name, "a")
