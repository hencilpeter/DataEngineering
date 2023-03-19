from collections import defaultdict

from src.util.custom_logger import CustomLogger
from src.data.payload import Payload
from src.data.payload_tag_name import PayloadTagName
from src.data.table_configuration_name import TableConfigurationName
from src.data.target_record_special_field_name import TargetRecordSpecialFieldName
from src.util.reader.config_reader import ConfigReader


class TargetRecord:
    def __init__(self, dict_message):
        self.payload = Payload(dict_message=dict_message)
        self.dict_buffer = defaultdict(lambda: -1)
        self.extracted_record_data_list = []
        self.fill_values()

    def get_placeholder_value(self, placeholder_key):
        start_index = placeholder_key.find("<<")
        end_index = placeholder_key.find(">>")

        actual_key = placeholder_key[start_index + 2: end_index - 2]
        if self.dict_buffer[placeholder_key] != -1:
            return self.dict.buffer[actual_key]
        return ""

    def get_placeholder_replaced_value(self, value):
        start_index = value.find("<<")
        if start_index == -1:
            return value
        end_index = value.find(">>")
        placeholder_key = value[start_index + 2: end_index]
        placeholder = value[start_index: end_index + 2]
        if self.dict_buffer[placeholder_key] == -1:
            CustomLogger.log_print_error(
                "no placeholder value found in the dictionary for the placeholder {}".format(placeholder))
            return value
        replaced_value = value.replace(placeholder, self.dict_buffer[placeholder_key])
        return replaced_value

    def fill_values(self):
        # table name
        self.dict_buffer[TargetRecordSpecialFieldName.TABLE_NAME] = self.payload.get_value_from_payload_tag(
            PayloadTagName.TABLE_NAME)
        # operation name
        tag_value = self.get_placeholder_replaced_value(PayloadTagName.OPERATION)
        self.dict_buffer[TargetRecordSpecialFieldName.SOURCE_TABLE_OPERATION] = self.payload.get_value_from_payload_tag(
            tag_value)
        # timestamp
        tag_value = self.get_placeholder_replaced_value(PayloadTagName.TIMESTAMP)
        self.dict_buffer[TargetRecordSpecialFieldName.MESSAGE_TIMESTAMP] = self.payload.get_value_from_payload_tag(
            tag_value)
        # received data
        table_name = self.dict_buffer[TargetRecordSpecialFieldName.TABLE_NAME]
        column_names = ConfigReader.get_tables_config(table_name, TableConfigurationName.COLUMNS)
        column_name_list = column_names.split(",")
        data = self.payload.get_value([PayloadTagName.DATA])
        self.extracted_record_data_list = [data[column_name] for column_name in column_name_list]

    def set_dict_value(self, **kwargs):
        for key, value in kwargs.items():
            self.dict_buffer[key] = value

    def get_target_record(self):
        writable_columns = [self.dict_buffer[TargetRecordSpecialFieldName.SOURCE_TABLE_OPERATION],
                            self.dict_buffer[TargetRecordSpecialFieldName.KAKFA_TOPIC],
                            self.dict_buffer[TargetRecordSpecialFieldName.KAFKA_TOPIC_OFFSET],
                            self.dict_buffer[TargetRecordSpecialFieldName.MESSAGE_TIMESTAMP]]

        writable_columns.extend(self.extracted_record_data_list)
        return writable_columns

    def get_table_name(self):
        return self.dict_buffer[TargetRecordSpecialFieldName.TABLE_NAME]

    def get_timestamp(self):
        return self.dict_buffer[TargetRecordSpecialFieldName.MESSAGE_TIMESTAMP]
