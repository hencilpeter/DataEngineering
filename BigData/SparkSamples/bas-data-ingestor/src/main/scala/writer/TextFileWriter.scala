/**
 * @author Hencil Peter
 */

package writer

import java.io.{FileNotFoundException, IOException}

import scala.sys.process._

class TextFileWriter extends BaseWriter {
  
   def execute(sourceFilePath: String, targetHDFSPath: String): Boolean = {
     //TODO: 
     
     try{
                  
      val hadoopMakeDirCommand = "hadoop fs -mkdir  %s".format(targetHDFSPath)
      val outputHDFSFolderCreate = hadoopMakeDirCommand.!!
      println("Folder %s created successfully!!!!".format(targetHDFSPath))
     
       
      val writeCommand = "hadoop fs -put -f %s %s".format(sourceFilePath, targetHDFSPath)
      println(writeCommand)
      
      val output = writeCommand.!!
      
      println("File %s copied to HDFS successfully!!!".format(sourceFilePath))
      
     }
     catch{
       case e: FileNotFoundException => println("Couldn't find that file.")
       case e: IOException => println("Got an IOException!"+ e.getMessage() + e.getStackTrace())
       
       false
     }
     finally{
     
     }
     
     true
   }
}

object TextFileWriter {
  def apply(): TextFileWriter = {
    new TextFileWriter()
  }
}