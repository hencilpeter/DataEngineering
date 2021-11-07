package util
import org.apache.hadoop.conf.Configuration
import org.apache.hadoop.fs.{FileSystem, Path}


import org.apache.log4j.Logger
import session.SparkSessionManager

class HdfsWriterUtil {
  @transient lazy val logger = Logger.getLogger(getClass.getName)
  
  def writeToHdfs(hdfsUri: String, filePath: String) {
    logger.info("Function writeToHdfs called...arguments=> hdfsUri : %s , filePath : %s".format(hdfsUri, filePath))
    
    logger.error("test..")
       
     val sparkSession = SparkSessionManager.GetSparkSession()
    
     var df = sparkSession.read.option("header", true).csv(filePath).toDF()
     
     println( "count : %d".format(df.count())  )
     println(df.limit(10).show())
     
    //df.columns.foreach( columnName => df = df.withColumnRenamed(columnName, columnName.replace(" ", "")))    
    //df.columns.foreach( columnName => df = df.withColumnRenamed(columnName, columnName.replace(" ", "") ) )
    //df.columns.foreach( columnName => df = df.withColumnRenamed(columnName, columnName.replace("(", "") ) )
   // df.columns.foreach( columnName => df = df.withColumnRenamed(columnName, columnName.replace(")", "") ) )
    println(df.limit(10).show())
   
    
    
    // df.columns = df.columns.foreach(colName => colName.replace(" ", "") )
     //df.write.csv("file:///home/osboxes/Hencil/Projects/data/queue/IBRDLoansHistoricalSampleData.csv")   
     //df.coalesce(1).write.format("csv").option("header", "true").option("sep", ",").mode("overwrite").csv("file:///home/osboxes/Hencil/Projects/data/queue/IBRDLoansHistoricalSampleData.csv")
     //.save("file:///home/osboxes/Hencil/Projects/data/queue/IBRDLoansHistoricalSampleData.csv")
    
     df.write.mode("overwrite").parquet(hdfsUri);
     println("File written to HDFS successfully")
     
    
//  val df = sc.read.option("header", true).csv("file:///home/osboxes/Desktop/shared_data/IBRD_Data/account.csv").toDF()
//  println(df.selectExpr("*").show(10))
//  var df2 = df.withColumnRenamed("Account Number", "AccountNumber")
//    .withColumnRenamed("Balance Amount", "BalanceAmount")
//    .withColumnRenamed("Date", "DateTest")
//  //write as ORC
//  //df2.write.orc("hdfs://quickstart-bigdata:8020/datalake/orc3")
//  df2.write.parquet("hdfs://quickstart-bigdata:8020/datalake/par")
//  df2.limit(10).show()
  
  
    
      
    //log details 
    
//    val filePath = new Path(nodeFilePath)
//    val conf = new Configuration()
//    conf.set("fs.defaultFS", hdfsUri)
//    val fileSystem = FileSystem.get(conf) 
//    val outputStream =  fileSystem.create(filePath)
    
    
  }
  
  def write(uri: String, filePath: String, data: Array[Byte]) = {
    
    
//    System.setProperty("HADOOP_USER_NAME", "Mariusz")
//    val path = new Path(filePath)
//    val conf = new Configuration()
//    conf.set("fs.defaultFS", uri)
//    val fs = FileSystem.get(conf)
//    val os = fs.create(path)
//    os.write(data)
//    fs.close()
  }
   
}