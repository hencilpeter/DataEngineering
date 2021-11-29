package main

import connection.SparkConnectionManager
import configuration._
import util._


object MainClassObject extends App{
  
  /*
   * 1. initialize values  //hard code for now 
   * 2. read configuration  //hard code for now  
   * 3. staging 
   * 4. Core Write 
   * */
 
	Console.println("BAS Data Ingestor Started....")
	
	//print data load configuration 
	DataLoadConfiguration.printDataLoadConfiguration()
  
	
	//Staging - TODO - create service object and embed the code
  val isStagingSuccess = StageFolderUtilImpl().execute(DataLoadConfiguration.fileRootDataQueuePathNormal, DataLoadConfiguration.hdfsStagingTargetPath)
  
  //Core Write - TODO - create service object and embed the code
	val isCoreWriteSuccess = CoreWriteUtilImpl().execute(DataLoadConfiguration.hdfsStagingTargetPath, "")
  
}