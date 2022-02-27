package spark_rdd

import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.types._


object EmptyRDDCreation {
  def main(args:Array[String]) :Unit = {
  	val sparkSession : SparkSession = SparkSession.builder().appName("EmptyRDDCreation").master("local[*]").getOrCreate()
  	sparkSession.sparkContext.setLogLevel("ERROR")
  	
  	//1. using empty RDD 
  	val emptyRDD = sparkSession.sparkContext.emptyRDD //emptyRDD[0]
  	println("emptyRDD[0] => " + emptyRDD + " |  Number of Partitions : " + emptyRDD.getNumPartitions) 
  	
  	
  	//2. mptyRDD[1]
  	val emptyRDDString = sparkSession.sparkContext.emptyRDD[String]  //emptyRDD[1]
  	println(s"emptyRDD[1] => $emptyRDDString    |   Number of Partitions :  ${emptyRDDString.getNumPartitions}")
  	
  	emptyRDDString .collect().foreach(f => println(f"RDD Value => $f"))
  	
  	//3. using Parallelize with empty sequence   - gives number of partitions 8 (TODO : check why?) 
  	val emptyRDDParallelize = sparkSession.sparkContext.parallelize(Seq.empty[String])
  	println(s"emptyRDDParallelize  : $emptyRDDParallelize  : Number of Partitions : ${emptyRDDParallelize.getNumPartitions}")
  	println(emptyRDDParallelize.getNumPartitions)
  	
  	
  	//4. empty Pair Type RDD 
  	type dataType =  (Int, String)
  	val emptyPairRDD = sparkSession.sparkContext.emptyRDD[dataType]
  	println( s"Empty Pair Type RDD : emptyPairRDD   |  Number of partitions : ${emptyPairRDD.getNumPartitions}")  
  	
  	
  	
  }
}