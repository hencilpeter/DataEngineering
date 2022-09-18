from module_constants import TableAction, MessageKey


class MessageUtil:
    def get_action_name(self, payload):
        action_name = TableAction.INSERT if payload[MessageKey.OLD_VALUE] is None else \
            (TableAction.DELETE if payload[MessageKey.NEW_VALUE] is None else TableAction.UPDATE)
        return action_name

    def get_record(self, payload):
        action = self.get_action_name(payload)
        if action == TableAction.INSERT or action == TableAction.UPDATE:
            return payload[MessageKey.NEW_VALUE]

        return payload[MessageKey.OLD_VALUE]

    def get_record_source_timestamp(self, payload):
        return payload[MessageKey.MESSAGE_SOURCE][MessageKey.TIMESTAMP_IN_MILLISECONDS]

    def get_record_kafka_timestamp(self, payload):
        return payload[MessageKey.TIMESTAMP_IN_MILLISECONDS]

    def get_writable_record(self, payload, list_column_order):
        dict_data = self.get_record(payload)
        record = [dict_data[column] for column in list_column_order]
        writable_columns = [self.get_record_source_timestamp(payload), self.get_record_kafka_timestamp(payload),
                            self.get_action_name(payload)]
        writable_columns.extend(record)
        return writable_columns
