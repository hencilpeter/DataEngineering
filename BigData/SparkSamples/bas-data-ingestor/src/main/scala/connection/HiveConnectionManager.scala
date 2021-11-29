package connection

import org.apache.log4j.Logger
import org.apache.spark.sql.hive._

object HiveConnectionManager {
	@transient lazy val logger = Logger.getLogger(getClass.getName)

	def getHiveContext() = {
		val sparkSession = SparkConnectionManager.GetSparkSession()
		val hiveContext = new HiveContext(sparkSession.sparkContext)

		hiveContext.setConf("hive.exec.dynamic.partition", "true") //TODO : read from configuration
		hiveContext.setConf("hive.exec.dynamic.partition.mode", "nonstrict") //TODO:

		hiveContext
	}
}