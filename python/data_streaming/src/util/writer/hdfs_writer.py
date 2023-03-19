from src.data.table_configuration_name import TableConfigurationName
from src.util.common_util import CommonUtil
from src.util.reader.config_reader import ConfigReader


# from pyspark.sql import Sparksession

class HdfsWriter:
    def __init__(self):
        # self.sparksession = Sparksession.builder.master("local[1]").appName ("SparkApp"). getorCreate()
        pass

    def write_data(self, target_record):
        target_path = CommonUtil.get_data_folder_path(target_record.get_table_name(),
                                                      target_record.get_timestamp())
        print("*****output data start******")
        print("target path : {}".format(target_path))
        write_mode = ConfigReader.get_tables_config(target_record.get_table_name(),
                                                    TableConfigurationName.HDFS_WRITE_MODE)
        write_format = ConfigReader.get_tables_config(target_record.get_table_name(),
                                                      TableConfigurationName.HDFS_WRITE_FORMAT)
        print("write mode : {}".format(write_mode))
        print("write_format : {}".format(write_format))
        message_buffer = target_record.get_target_record()
        print("message buffer : {}".format(message_buffer))
        print("****output data end******")
        # df = self.sparksession.createDataFrame (message_buffer)
        # df.coalesce(1).write.format("csv"). option("header", "false") .mode('append').save("hdfs://localhost:9000/data/')
        # df.coalesce(1).write.format(write_format).option("header", "false").mode(write_mode).save(target_path)
        pass
