/**
  * @author Hencil Peter
  *
  */

package reader

import org.apache.spark.sql.DataFrame
import connection.SparkConnectionManager
import configuration.DataLoadConfiguration
import org.apache.spark.sql.functions.lit

class CSVReader extends BaseReader {

	def execute(filePath: String): DataFrame = {
		val sparkSession = SparkConnectionManager.GetSparkSession()

		//dynamic partition configuration
		sparkSession.conf.set("spark.sql.sources.partitionOverwriteMode", "dynamic")
		
		val dataFrame = sparkSession.read.option("header", true)
			.option("delimiter", ",").csv(filePath)

		val dataFrameWithOnePartitionColumn = dataFrame
			.withColumn("DataGenerateDate", lit(DataLoadConfiguration.dataGenerateDate))

		val dataFrameWithTwoPartitionColumn = dataFrameWithOnePartitionColumn
			.withColumn("DataLoadTimestamp", lit(DataLoadConfiguration.dataLoadTimestamp))

		dataFrameWithTwoPartitionColumn
	}
}

object CSVReader {
	def apply() = {
		new CSVReader()
	}
}