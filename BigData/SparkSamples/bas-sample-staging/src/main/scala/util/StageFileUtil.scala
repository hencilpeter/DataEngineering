package util

import writer.ParquetWriter
import java.io.File
import scala.collection.immutable._
import writer.TextFileWriter

import scala.sys.process._


import session.SparkSessionManager



class StageFileUtil {
  
  def execute(sourceFilePath:String, targetHDFSPath: String):Boolean ={
    
    //TODO : check extension
    val sourceDirectory = new File(sourceFilePath)
    var fileList: List[File] = List()
    
    //create a new directory in HDFS 
     val hadoopMakeDirCommand = "hadoop fs -mkdir  %s".format(targetHDFSPath)
     //println(hadoopMakeDirCommand)

     val output = hadoopMakeDirCommand.!!
     
     println(hadoopMakeDirCommand + " command executed successfully!!!" )
     
    if (sourceDirectory.exists() && sourceDirectory.isDirectory()) {
      fileList = sourceDirectory.listFiles().filter(_.isFile()).toList
    }
    
    if ( fileList.length == 0)
      false
    
    val writerTextFileObject = TextFileWriter() 
    
    //copy all the files to HDFS directory 
    fileList.foreach{file => 
       //TODO : created the target HDFS with folder name. need to customize.  
       var fileNameWithoutExtension = file.getPath.substring(file.getPath.lastIndexOf("/")+1, file.getPath.lastIndexOf("."))          
        val targetHDFSPathWithFileName = targetHDFSPath + "/" + fileNameWithoutExtension + "/"
           
        writerTextFileObject.execute(file.getPath, targetHDFSPathWithFileName)
      }
    
    
    val sparkSession = SparkSessionManager.GetSparkSession()
    
     //create staging tables 
     fileList.foreach{ fileName => 
       val qualifiedFilePath = "file://" + fileName
       
       var df = sparkSession.read.option("header", true).csv(qualifiedFilePath).limit(0).toDF()
       
       val columnNames = df.columns.mkString(" string,") + " string"
       
       val fileNameWithoutExtension = fileName.getName.substring(0, fileName.getName.lastIndexOf("."))
       
       //CSV filename without extension        
       //val createExternalTableCommand = "CREATE EXTERNAL TABLE %s_%s(%s) ROW FORMAT DELIMITED FIELDS TERMINATED BY ',' STORED AS TEXTFILE LOCATION 'hdfs://quickstart-bigdata:8020%s/%s' TBLPROPERTIES (\"skip.header.line.count\"=\"1\");"
       //.format(fileNameWithoutExtension, targetHDFSPath.substring(targetHDFSPath.lastIndexOf("/") + 1 ), columnNames, targetHDFSPath, fileNameWithoutExtension)
       
       val createExternalTableCommand = "CREATE EXTERNAL TABLE staging.%s_%s(%s) ROW FORMAT DELIMITED FIELDS TERMINATED BY ',' STORED AS TEXTFILE LOCATION 'hdfs://quickstart-bigdata:8020%s/%s'"
           .format(fileNameWithoutExtension, targetHDFSPath.substring(targetHDFSPath.lastIndexOf("/") + 1 ), columnNames, targetHDFSPath, fileNameWithoutExtension)
       
       sparkSession.sql(createExternalTableCommand)
       
       println(createExternalTableCommand)
       println ("Staging table %s_%s created successfully!!!".format(fileNameWithoutExtension, targetHDFSPath.substring(targetHDFSPath.lastIndexOf("/") + 1 )))
       
     }
          
    true
  }
}

object StageFileUtil{
  
  def apply(): StageFileUtil = {
   println("Stage File Util Called...")
   return new StageFileUtil()
  }
  
}