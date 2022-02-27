package spark_rdd

import org.apache.spark.rdd.RDD
import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.types.StructType
import org.apache.spark.sql.types.StructField
import org.apache.spark.sql.types.IntegerType



object RDDCreation {
	def main(args: Array[String]): Unit = {

		val sparkSession: SparkSession = SparkSession.builder()
			.master("local[3]")
			.appName("SparkByExample")
			.getOrCreate()

	  sparkSession.sparkContext.setLogLevel("ERROR")
	  
		//1. RDD simple example - using parallelize  	(List or Seq can be used)
		val rdd = sparkSession.sparkContext.parallelize(Seq(1, 2, 3, 4, 5, 6))
		val rddCollect: Array[Int] = rdd.collect()
    //print elements 
		println("Action: RDD converted to Array[Int] : ")
		rddCollect.foreach(println)
		//no of partitions 
		println("Number of partitions : " + rdd.getNumPartitions)
		//get first number 
		println("Action first first element : " + rdd.first())
		
		val rdd2 = rdd.coalesce(2)
		println("Number of partitions : " + rdd2.getNumPartitions)
		
		//2.using  textFile and wholeTextFiles
		val rddSingleTextFile = sparkSession.sparkContext.textFile("/home/osboxes/Hencil/data/sparkbyexample/csv/text01.txt")
		//we can include additional files using ";" separator. e.g /home/osboxes/Hencil/data/sparkbyexample/csv/text01.txt; /home/osboxes/Hencil/data/sparkbyexample/csv/text02.txt
		println("Using textFile (text01.txt) -> read single file")
		println("===============================================")
		rddSingleTextFile.collect().foreach(println)
		
		println("Using textFile -> read multiple files")
		println("===============================================")
		val rddMultipleTextFiles = sparkSession.sparkContext.textFile("/home/osboxes/Hencil/data/sparkbyexample/csv/*")
		rddMultipleTextFiles.collect().foreach(println)
		
		//wholeTextFiles function 
		println("Using wholeTextFiles (text01.txt) -> read single file")
		println("===============================================")
		val rddSingleTextFileUsingWholeTextFiles = sparkSession.sparkContext.wholeTextFiles("/home/osboxes/Hencil/data/sparkbyexample/csv/text01.txt")
		
		rddSingleTextFileUsingWholeTextFiles.collect().foreach(f => println(f" f._1:  ${f._1}  f._2  ${f._2}"))
		
		println("Using wholeTextFiles  -> read multiple file")
		println("===============================================")
		val rddMultipleFileUsingWholeTextFiles = sparkSession.sparkContext.wholeTextFiles("/home/osboxes/Hencil/data/sparkbyexample/csv/*")
		rddMultipleFileUsingWholeTextFiles.collect().foreach(f => println(f" f._1 :  ${f._1}  => f._2  : ${f._2} "))
		
		//create RDD from existing RDD
		println("RDD from exsting RDD")
		println("===============================================")
		val rddFromExistingRDD = rddMultipleFileUsingWholeTextFiles.map(f => "f._1 : " + f._1  + " f._2 : " + f._2 )
		rddFromExistingRDD.collect().foreach( f => println("Row Value : " + f))
		
		//create RDD from existing Dataframe and Dataset
		println("RDD from existing Dataframe and Dataset")
		
		//RDD from Dataframe 
		val rddSample = sparkSession.sparkContext.parallelize(Seq(1,2,3,4,5,6,7,8,9,10,11,12,13))
		
		//below import is essential to enable Dataframe frame related functions in RDD (e.g toDF) 
		import sparkSession.implicits._
		val dfSample = rddSample.toDF()
		dfSample.show()
		//now get the rdd 
		val rddFromDataFrame = dfSample.rdd
		val rddData = rddFromDataFrame.collect()
		for (r <- rddData){
			println(r)
		}
		
		
		//RDD from Dataset 
		val dsSample = rddSample.toDS()
		val rddFromDS = dsSample.rdd
		
		
	}
}