package util

import java.io.File

import scala.sys.process._

import writer._

import connection._


class StageFolderUtilImpl extends StageFolderUtil {

	def execute(sourceFilePath: String, targetHDFSPath: String): Boolean = {
		//TODO : check extension
		val sourceDirectory = new File(sourceFilePath)
		var fileList: List[File] = List()

		//create a new directory in HDFS (including all the sub directories)
		val hadoopMakeDirCommand = "hadoop fs -mkdir -p %s".format(targetHDFSPath)

		val output = hadoopMakeDirCommand.!!

		println(hadoopMakeDirCommand + " command executed successfully!!!")

		if (sourceDirectory.exists() && sourceDirectory.isDirectory()) {
			fileList = sourceDirectory.listFiles().filter(_.isFile()).toList
		}

		if (fileList.length == 0)
			false
			
			
	 val writerTextFileObject = TextFileWriter() 
   Console.println("Preparing the Stage the data (Queue to Stage) ")
   Console.println("============================================= ")
    //copy all the files to HDFS directory 
    fileList.foreach{file => 
       //TODO : created the target HDFS with folder name. need to customize.  
       var fileNameWithoutExtension = file.getPath.substring(file.getPath.lastIndexOf("/") + 1, file.getPath.lastIndexOf("."))          
       val targetHDFSPathWithFileName = targetHDFSPath + "/" + fileNameWithoutExtension + "/"
           
        writerTextFileObject.execute(file.getPath, targetHDFSPathWithFileName)
      }
    
    Console.println("Total file copied to staging path : %d".format(fileList.length))
		Console.println("External tables being created.")
		Console.println("=============================")
    val sparkSession = SparkConnectionManager.GetSparkSession()

     //create staging tables 
     fileList.foreach{ fileName => 
       val qualifiedFilePath = "file://" + fileName
       
       var df = sparkSession.read.option("header", true).csv(qualifiedFilePath).limit(0).toDF()
       
       val columnNames = df.columns.mkString(" string,") + " string"
       
       println("columnNames" + columnNames)
       val fileNameWithoutExtension = fileName.getName.substring(0, fileName.getName.lastIndexOf("."))
       
       val createExternalTableCommand = "CREATE EXTERNAL TABLE staging.%s_%s(%s) ROW FORMAT DELIMITED FIELDS TERMINATED BY ',' STORED AS TEXTFILE LOCATION '%s/%s'"
           .format(fileNameWithoutExtension, targetHDFSPath.substring(targetHDFSPath.lastIndexOf("/") + 1 ), columnNames, targetHDFSPath, fileNameWithoutExtension)
       
       sparkSession.sql(createExternalTableCommand)
       
       //println(createExternalTableCommand)
       println ("Staging table %s_%s created successfully!!!".format(fileNameWithoutExtension, targetHDFSPath.substring(targetHDFSPath.lastIndexOf("/") + 1 )))
       
		
     }  
		true
	}
}

object StageFolderUtilImpl {
	def apply(): StageFolderUtilImpl = {
		Console.println("Stage Folder object being created...")
		new StageFolderUtilImpl()
	}
}