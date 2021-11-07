package writer

import org.apache.hadoop.conf.Configuration
import org.apache.hadoop.fs.FileSystem
import org.apache.hadoop.fs.Path

import java.io.{FileNotFoundException, IOException}

import scala.sys.process._

class ParquetWriter extends BaseWriter {
  
  //writeFileToHDFS - TODO : send arg with compression type, file type 
  def execute(sourceFilePath: String, targetHDFSPath: String): Boolean = {
    //TODO: get file config 
    val sourcePath = new Path(sourceFilePath)
    val targetPath = new Path(targetHDFSPath)
    
    try
    {
        val hadoopConfig = new Configuration()
        val fileSystemInstance = FileSystem.get(hadoopConfig) 
        
        //fileSystemInstance.copyFromLocalFile(sourcePath, targetPath)
        
        val cmd = "hadoop fs -put -f /home/osboxes/Hencil/Projects/data/arrival/IBRDLoansHistoricalSampleData.csv /datalake/staging"
        //val cmd = "uname -a"
        
        val output = cmd.!!
        println("Result : "+ output)
    }
    catch {
      //TODO : catch
       case e: FileNotFoundException => println("Couldn't find that file.")
       case e: IOException => println("Got an IOException!"+ e.getMessage() + e.getStackTrace())
       
       return false
    }
    finally{
       //TODO: fill finally 
    }
    return true
  }
  
}

object ParquetWriter {
  def apply():ParquetWriter = {
    new ParquetWriter()
  }
}