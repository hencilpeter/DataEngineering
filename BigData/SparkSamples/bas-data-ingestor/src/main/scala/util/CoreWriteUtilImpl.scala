/**
  * @author Hencil Peter
  *
  */

package util
import configuration._
import collection._
import connection._

import reader.CSVReader

import org.apache.hadoop.fs.{ FileSystem, Path }
import org.apache.hadoop.conf.Configuration
import transformation.{TransformationRuleEngineImpl, TransformationRuleExtractorImpl}

class CoreWriteUtilImpl extends CoreWriteUtil {

	def execute(stagedHDFSPath: String, coreHDFSPath: String): Boolean = {
		println("Core Write Starts")

		val hiveContext = HiveConnectionManager.getHiveContext()

		val conf = new Configuration()
		
		conf.set("fs.defaultFS", "hdfs://quickstart-bigdata:8020")

		val fs = FileSystem.get(conf)
		val status = fs.listStatus(new Path(stagedHDFSPath))

		status.foreach(x => {

			if (x.isDirectory()) {
				//read staged  file
				var coreData = CSVReader().execute(x.getPath.toString())
				
				
				//core table and location 
				val lastBackSlashIndex = x.getPath.toString().lastIndexOf("/") + 1
				val stagingTableFolder = x.getPath.toString().substring(lastBackSlashIndex)
				val coreTableName = TempCoreTableMap.coreTableFolder(stagingTableFolder)
				val coreTableLocation = DataLoadConfiguration.hdfsCoreRootPath + coreTableName;
				val coreTableLocationNormal = DataLoadConfiguration.hdfsCoreRootPathNormal + coreTableName;

				//apply transformation  
				
				//get the file Id
				val fileId = TempFileMap.fileAndId.get(stagingTableFolder)
				
				//apply transformation for the current file id 
				coreData = TransformationRuleEngineImpl().applyTransformation(coreData, fileId.get, TransformationRuleExtractorImpl())
				//coreData.show(10)
						
				//println(coreTableLocation)

				//write core data
				coreData.write.partitionBy("DataGenerateDate", "DataLoadTimestamp")
					.format("csv").option("delimiter", ",")
					.mode(org.apache.spark.sql.SaveMode.Overwrite)
					.save(coreTableLocation)

				//add partition 	
				val hiveCommand = "ALTER TABLE " + TempCoreTableMap.coreTable(stagingTableFolder) + " ADD IF NOT EXISTS PARTITION (DataGenerateDate='" + DataLoadConfiguration.dataGenerateDate + "', DataLoadTimestamp='" + DataLoadConfiguration.dataLoadTimestamp + "') LOCATION '" + coreTableLocationNormal + "/DataGenerateDate=" + DataLoadConfiguration.dataGenerateDate + "/DataLoadTimestamp=" + DataLoadConfiguration.dataLoadTimestamp + "'"
				hiveContext.sql(hiveCommand)
				
				println(hiveCommand)

			}

		})

		true
	}
}

object CoreWriteUtilImpl {
	def apply(): CoreWriteUtilImpl = {
		new CoreWriteUtilImpl()
	}
}