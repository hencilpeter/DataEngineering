package main

import util.HdfsWriterUtil
import org.apache.hadoop.hdfs.client.HdfsUtils
import util.StageFileUtil
import java.util.Calendar
import java.text.SimpleDateFormat
import session.SparkSessionManager

//import org.apache.spark.sql.hive.HiveContext
import org.apache.spark.sql.{ Row, SaveMode, SparkSession }

import org.apache.spark.sql.SQLContext

import org.apache.spark.sql.hive._

object MainClass extends App {
  println("Staging")
  // @transient var hiveContext: HiveContext = null
  val filePath = "/home/osboxes/Hencil/Projects/data/queue/"
  val hdfsUri = "/datalake/staging/"
  //staging area
  val dateTimeFormat = new SimpleDateFormat("YYYYMMddHHmmss")
  val currentStagingFolder = dateTimeFormat.format(Calendar.getInstance.getTime())
  val stageUtil = StageFileUtil()

  stageUtil.execute(filePath, hdfsUri + currentStagingFolder)

  val sparkSession = SparkSessionManager.GetSparkSession()
  //sparkSession.sql("CREATE EXTERNAL TABLE AccountSampleData_Y20211107154143(AccountOpenDate string,AccountNumber string,Region string,Country string,BalanceAmount string) STORED AS TEXTFILE LOCATION 'hdfs://quickstart-bigdata:8020/datalake/staging/20211107154143/AccountSampleData' ;")
  
  //below two lines work 
  //sparkSession.sql("CREATE EXTERNAL TABLE AccountSampleData_B20211107154143(AccountOpenDate string,AccountNumber string,Region string,Country string,BalanceAmount string) ROW FORMAT DELIMITED FIELDS TERMINATED BY ',' STORED AS TEXTFILE LOCATION 'hdfs://quickstart-bigdata:8020/datalake/staging/20211107154143/AccountSampleData' TBLPROPERTIES (\"skip.header.line.count\"=\"2\")   ")
 //sparkSession.sql("SELECT * FROM AccountSampleData_B20211107154143").show()
  
  //hive - below two lines work 
  val hiveContext = new HiveContext(sparkSession.sparkContext)
  //hiveContext.sql("CREATE EXTERNAL TABLE default.AccountSampleData_D20211107154143(AccountOpenDate string,AccountNumber string,Region string,Country string,BalanceAmount string) ROW FORMAT DELIMITED FIELDS TERMINATED BY ',' STORED AS TEXTFILE LOCATION 'hdfs://quickstart-bigdata:8020/datalake/staging/20211107154143/AccountSampleData' TBLPROPERTIES (\"skip.header.line.count\"=\"2\")")
   
   hiveContext.sql ("use staging")
   println(hiveContext.sql ("show tables ").toDF().show(false))
  
  
  
  //spark.sql("create table my_table(" + columns + ")
  // row format delimited fields terminated by '|' location '/my/hdfs/location'");

  //val sqlContext =  SQLContext.getOrCreate(sparkSession.sparkContext)

  //sqlContext.sql("select * from accountsampledata_20211107120822").show()
  //sqlContext.sql("select * from AccountSampleData_20211107122021").show()

  // val query = sparkSession.sqlContext.sql("select * from accountsampledata_20211107120822")
  //println(query)

  //hiveContext = new HiveContext(sparkSession.sparkContext)
  //val emptyDataFrame = hiveContext.sql("select * from accountsampledata_20211107120822 limit 10")
  //println(emptyDataFrame.show())

  // /home/osboxes/Hencil/Projects/data/queue/AccountSampleData.csv /datalake/staging/20211107104123
  // /home/osboxes/Hencil/Projects/data/queue/AccountSampleData.csv

  //val test1 = "/home/osboxes/Hencil/Projects/data/queue/AccountSampleData.csv"
  //println(test1.substring(test1.lastIndexOf("/") + 1))

  // println(dateTimeFormat.format(Calendar.getInstance.getTime()))

  //core merge

}