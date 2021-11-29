package util
import configuration._
import collection._
import connection._

import org.apache.spark.sql.functions.lit

import org.apache.hadoop.fs.{FileSystem, Path}
import org.apache.hadoop.conf.Configuration
//import scala.collection.parallel.ParIterableLike.Foreach

class CoreWriteUtilImpl extends CoreWriteUtil{
  
	def execute(stagedHDFSPath: String, coreHDFSPath: String): Boolean = {
		println("Core Write Starts")
		//loop the sub folders 
		
	 val sparkSession = SparkConnectionManager.GetSparkSession()
	 val hiveContext = HiveConnectionManager.getHiveContext() 
	   
	   //dynamic partition configuration
     //hiveContext.setConf("hive.exec.dynamic.partition", "true")
     //hiveContext.setConf("hive.exec.dynamic.partition.mode", "nonstrict")
     sparkSession.conf.set("spark.sql.sources.partitionOverwriteMode", "dynamic")

	 
		val conf = new Configuration()
		conf.set("fs.defaultFS", "hdfs://quickstart-bigdata:8020")
				
		val fs = FileSystem.get(conf)
		//val status = fs.listStatus(new Path("hdfs://quickstart-bigdata:8020/datalake/data/staging/202111/apac/sg/crm/20211128092110"))
		val status = fs.listStatus(new Path(stagedHDFSPath))
		
		
		status.foreach(x=> {
			val dataFrame = sparkSession.read.option("header", true).option("delimiter", ",").csv(x.getPath.toString())
			val dataFrameWithOnePartitionColumn = dataFrame.withColumn("DataGenerateDate", lit(DataLoadConfiguration.dataGenerateDate))
      val dataFrameWithTwoPartitionColumn = dataFrameWithOnePartitionColumn.withColumn("DataLoadTimestamp", lit(DataLoadConfiguration.dataLoadTimestamp))
      
      if (x.isDirectory()) {
      	val lastBackSlashIndex = x.getPath.toString().lastIndexOf("/") + 1
      	val stagingTableFolder = x.getPath.toString().substring(lastBackSlashIndex) 
      	val coreTableName = TempCoreTableMap.coreTableFolder(stagingTableFolder)
      	val coreTableLocation = DataLoadConfiguration.hdfsCoreRootPath + coreTableName;
      	val coreTableLocationNormal = DataLoadConfiguration.hdfsCoreRootPathNormal + coreTableName;

      	println(coreTableLocation)
      	
      	dataFrameWithTwoPartitionColumn.write.partitionBy("DataGenerateDate", "DataLoadTimestamp")
      	.format("csv").option("delimiter", ",")
      	.mode(org.apache.spark.sql.SaveMode.Overwrite)
      	.save(coreTableLocation)
      	
      	
      	val hiveCommand ="ALTER TABLE " + TempCoreTableMap.coreTable(stagingTableFolder) + " ADD IF NOT EXISTS PARTITION (DataGenerateDate='" + DataLoadConfiguration.dataGenerateDate + "', DataLoadTimestamp='" + DataLoadConfiguration.dataLoadTimestamp + "') LOCATION '" + coreTableLocationNormal + "/DataGenerateDate=" + DataLoadConfiguration.dataGenerateDate + "/DataLoadTimestamp=" + DataLoadConfiguration.dataLoadTimestamp + "'"
      	hiveContext.sql (hiveCommand)
      	println(hiveCommand)
      	
      	
      }
      
//			if (x.isDirectory() && x.getPath.toString().endsWith("AccountSampleData") ){
//
//				dataFrameWithTwoPartitionColumn.write.partitionBy("DataGenerateDate", "DataLoadTimestamp").format("csv").option("delimiter", ",").mode(org.apache.spark.sql.SaveMode.Overwrite).save("hdfs://quickstart-bigdata:8020/data/bos/core/CRM_AccountSampleData")
//				dataFrameWithTwoPartitionColumn.show(10)
//				
//				val hiveCommand ="ALTER TABLE coredb.CRM_AccountSampleData ADD IF NOT EXISTS PARTITION (DataGenerateDate='"+DataLoadConfiguration.dataGenerateDate + "', DataLoadTimestamp='" + DataLoadConfiguration.dataLoadTimestamp + "') LOCATION '/data/bos/core/CRM_AccountSampleData/DataGenerateDate=" + DataLoadConfiguration.dataGenerateDate + "/DataLoadTimestamp=" + DataLoadConfiguration.dataLoadTimestamp + "'"
//				
//				println("hive command "+ hiveCommand)
//				hiveContext.sql (hiveCommand)	
//				println("Partition added successfully for the table CRM_AccountSampleData!!!")
//        
//			}
//			else if (x.isDirectory() && x.getPath.toString().endsWith("IBRDLoansHistoricalSampleData") )
//								
//				dataFrameWithTwoPartitionColumn.write.partitionBy("DataGenerateDate", "DataLoadTimestamp").format("csv").option("delimiter", ",").mode(org.apache.spark.sql.SaveMode.Overwrite).save("hdfs://quickstart-bigdata:8020/data/bos/core/CRM_IBRDLoansHistoricalSampleData")
//				dataFrameWithTwoPartitionColumn.show(10)
//				
//				val hiveCommand ="ALTER TABLE coredb.CRM_IBRDLoansHistoricalSampleData ADD IF NOT EXISTS PARTITION (DataGenerateDate='"+DataLoadConfiguration.dataGenerateDate + "', DataLoadTimestamp='" + DataLoadConfiguration.dataLoadTimestamp + "') LOCATION '/data/bos/core/CRM_IBRDLoansHistoricalSampleData/DataGenerateDate=" + DataLoadConfiguration.dataGenerateDate + "/DataLoadTimestamp=" + DataLoadConfiguration.dataLoadTimestamp + "'"
//				
//				println("hive command "+ hiveCommand)
//				hiveContext.sql (hiveCommand)	
//				println("Partition added successfully!!!")
//			

		})
		
  	true	
  }
}

object CoreWriteUtilImpl{
	def apply():CoreWriteUtilImpl ={
		new CoreWriteUtilImpl()
	}
}