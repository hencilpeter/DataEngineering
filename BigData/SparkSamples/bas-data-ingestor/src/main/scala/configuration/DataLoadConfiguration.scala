/**
  * @author Hencil Peter
  *
  */

package configuration

import java.util.Calendar
import java.text.SimpleDateFormat

object DataLoadConfiguration {
	//TODO : load config data from DB (except DB name and initialize the variables)
	val configDatabaseName = "BasEtlConfig"

	val dateTimeFormat = new SimpleDateFormat("YYYYMMddHHmmss")
	val dateFormat = new SimpleDateFormat("YYYYMM")

	val region = "apac"
	val country = "sg"
	val application = "crm"

	//date/time
	val dataLoadDate = dateFormat.format(Calendar.getInstance.getTime())
	val dataLoadTimestamp = dateTimeFormat.format(Calendar.getInstance.getTime())
	val dataGenerateDate = "20211201"

	//file
	val fileRootDataPathNormal = "/home/osboxes/Hencil/Projects/data"
	val fileRootDataQueuePathNormal = fileRootDataPathNormal + "/queue"
	val fileRootDataPathQualified = "file:///home/osboxes/Hencil/Projects/data"
	val fileArrivalRootDataPathQualified = fileRootDataPathQualified + "/arrival"
	val fileQueueRootDataPathQualified = fileRootDataPathQualified + "/queue"

	//hdfs root path
	val nameNode = "hdfs://quickstart-bigdata:8020"
	val hdfsDataRootPath = "hdfs://quickstart-bigdata:8020/datalake/data"
	val hdfsHiveRootPath = hdfsDataRootPath + "/hive"
	val hdfsStagingRootPath = "hdfs://quickstart-bigdata:8020/datalake/data" + "/staging"
	val hdfsCoreRootPath = "hdfs://quickstart-bigdata:8020/data/bos" + "/core/"
	val hdfsCoreRootPathNormal = "/data/bos" + "/core/"
	val hdfsStagingTargetPath = hdfsStagingRootPath + "/" + dataLoadDate + "/" + region + "/" + country + "/" + application + "/" + dataLoadTimestamp

	def printDataLoadConfiguration() = {
		Console.println("Dataload Configuration")
		Console.println("======================")
		Console.println("Plain Queue Path : " + fileRootDataQueuePathNormal)
		Console.println("Qualified Queue Path : " + fileQueueRootDataPathQualified)
		Console.println("Qualified Staging Path : " + hdfsStagingTargetPath)
		Console.println("======================")
	}

}