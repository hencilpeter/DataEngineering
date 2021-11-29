package connection

import org.apache.log4j.Logger
import org.apache.spark.sql.SparkSession

object SparkConnectionManager {
	@transient lazy val logger = Logger.getLogger(getClass.getName)

	def GetSparkSession(): SparkSession = {
		logger.info("Function SparkSession called...")

		val sparkSession = SparkSession.builder().master("local[1]")
			.appName("bas-sample-staging") // TODO: load from config
			.enableHiveSupport() //essential to access hive table
			.config("hive.metastore.uris", "thrift://quickstart-bigdata:9083") // TODO: load from config
			.getOrCreate()

		return sparkSession
	}
}