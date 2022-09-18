# author : J Hencil Peter
# Description : Constants for Kafka consumer

class TableAction:
    INSERT = "insert"
    DELETE = "delete"
    UPDATE = "update"


class MessageKey:
    PAYLOAD = "payload"
    OLD_VALUE = "before"
    NEW_VALUE = "after"
    TIMESTAMP_IN_MILLISECONDS = "ts_ms"
    MESSAGE_SOURCE = "source"


class Configuration:
    FILEPATH = "custom_config"
    FILENAME = "config_tables.yml"
    DEFAULT_SECTION = "default"
    COLUMNS_HEADER_KEY = "columns"
    DATABASE_NAME = "database_name"
    TABLE_SCHEMA = "table_schema"
