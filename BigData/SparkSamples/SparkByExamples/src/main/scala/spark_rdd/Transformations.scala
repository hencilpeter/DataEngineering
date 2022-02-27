package spark_rdd

import org.apache.spark.rdd.RDD
import org.apache.spark.sql.SparkSession
import org.apache.spark.sql._
import org.apache.spark.sql.types._

object Transformations {
  def main(args:Array[String]): Unit = {
   
  	
  	val sparkSession : SparkSession =  SparkSession.builder().appName("RDD Transformations").master("local[*]").getOrCreate()
  	sparkSession.sparkContext.setLogLevel("ERROR")
  	
  	
  	
  	//create simple RDD 
  	val rddTextFile = sparkSession.read.text("/home/osboxes/Hencil/data/sparkbyexample/test.txt")
  	
  	println("1. Narrow Transformations......")
  	println("*********************************")
  	//map(), mapPartition(), flatMap(), filter(), union()
  	println(s"Five lines of file : \n" + rddTextFile.take(5).mkString("\n"))
  	
  	val rddFiveLines = rddTextFile.take(5)
  	println("1. Map function")
  	println("----------------")
  	rddFiveLines.map(f => println(f+"_NewText"))
  	
  	println("2. MapPartition")
  	println("---------------")

  	
  	import sparkSession.implicits._
  	
  	val structureData = Seq (Row("Dan Basset", 25, "USA"),
  			                     Row("Tom Brenner", 40, "Australia"),
  			                     Row("Subbu",50, "Chennai")
  			                    )
   val structureSchema = StructType( 
  		 Array(StructField("Name", StringType, false), 
  		 StructField("Age", IntegerType, false), 
  		 StructField("Country", StringType, false)
  		 ))
  	val rddTable = sparkSession.sparkContext.parallelize(structureData)
  	
  	val dfTable = sparkSession.createDataFrame(rddTable, structureSchema)
  	println(s"Sample Dataframe \n")
  	dfTable.show()
    //rddTable.mapPartitions(f => println(f.collect())

  	
  			
  	
  	
  	println("2. Wider Transformations......")
  	println("*********************************")
  }
}