class TableConfigurationValue:
    HDFS_WRITE_MODE_APPEND = "append"
    HDFS_WRITE_MODE_OVERWRITE = "overwrite"
    HDFS_WRITE_FORMAT_CSV = "csv"
    HDFS_WRITE_FORMAT_ORC = "orc"
    PARTITION_FREQUENCY_HOUR = "hour"
    PARTITION_FREQUENCY_MINUTE = "minute"
    PARTITION_FREQUENCY_DAILY = "daily"

    def __init__(self):
        pass
