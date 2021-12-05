/**
  * @author Hencil Peter
  *
  */

package main

import connection.SparkConnectionManager
import configuration._
import util._

import org.apache.log4j.Logger

object MainClassObject extends App {

	/*
   * 1. initialize values  //hard code for now
   * 2. read configuration  //hard code for now
   * 3. staging
   * 4. Transformation  
   * 5. Core Write
   * */
	
 @transient lazy val logger = Logger.getLogger(getClass.getName)
 
 logger.info("BAS Data Ingestor Started....")

	//print data load configuration
	DataLoadConfiguration.printDataLoadConfiguration()

	//Staging - TODO - create service object and embed the code
	val isStagingSuccess = StageFolderUtilImpl().execute(DataLoadConfiguration.fileRootDataQueuePathNormal, DataLoadConfiguration.hdfsStagingTargetPath)

	//Core Write - TODO - create service object and embed the code
	val isCoreWriteSuccess = CoreWriteUtilImpl().execute(DataLoadConfiguration.hdfsStagingTargetPath, "")
	Console.println("BAS Data Ingestion Completed....")

}