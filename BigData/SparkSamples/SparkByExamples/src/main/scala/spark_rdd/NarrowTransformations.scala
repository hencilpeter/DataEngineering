package spark_rdd

import org.apache.spark.sql.SparkSession
import org.apache.spark.rdd.RDD

object NarrowTransformations {
	
	def printTuplesStringIntType(rddTuples:RDD[(String,Int)] , sep:String, description:String) : Unit = {
		println(description)
		println("***************************************************************")
		rddTuples.foreach(println)
		println("****************************************************************")
	}
	def printTuplesStringIntType2(rddTuples:RDD[(Int, String)] , sep:String, description:String) : Unit = {
		println(description)
		println("***************************************************************")
		rddTuples.foreach(println)
		println("****************************************************************")
	}
	
	
	def printStringArrayType(rddString:RDD[String] , sep:String, description:String) : Unit = {
		println(description)
		println("***************************************************************")
		rddString.foreach(println)
		println("****************************************************************")
	}

		def printIntArrayType(rddString:RDD[Int] , sep:String, description:String) : Unit = {
		println(description)
		println("***************************************************************")
		rddString.foreach(println)
		println("****************************************************************")
	}

		
	def sumfuncpartition(numbers: Iterator[Int]): Iterator[Int] =
		{
			var sum = 1
			println("function called--------------------")
			while (numbers.hasNext) {
				//sum = sum + numbers.next()
				val num = numbers.next()
				println("Num : " + num)
				sum = sum + num
			}
			println(s"Sum***** : ${sum}"  )
			return Iterator(sum)
		}

	
		
  def main(args:Array[String]): Unit ={
  	println("Narrow Transformations Example...")
  	
    val sparkSession:SparkSession = SparkSession.builder().appName("Narrow Transformation").master("local[*]").getOrCreate()
    
    sparkSession.sparkContext.setLogLevel("ERROR")
    
    val fileRDD = sparkSession.read.text("/home/osboxes/Hencil/data/sparkbyexample/test.txt").rdd 
    val tennisChampions2018To2021RDD = sparkSession.sparkContext.parallelize(
    																	Seq("Daniil Medvedev","Novak Djokovic","Novak Djokovic","Novak Djokovic","Rafael Nadal"	
																					,"Dominic Thiem"	,"Novak Djokovic"	,"Rafael Nadal"	,"Novak Djokovic"	,"Rafael Nadal"	
																					,"Novak Djokovic"	,"Novak Djokovic"	,"Novak Djokovic"	,"Rafael Nadal"	,"Roger Federer"))
    val numbersRDD = sparkSession.sparkContext.parallelize(List(1,2,3,4,5,6,7,8,9))
    val numbers = numbersRDD.collect()
    
    val flowerRatingWithNameRDD = sparkSession.sparkContext.parallelize(Seq( (7, "Rose"), (4, "Jasmine"),(7, "Lilly"), (3, "Lotus")))
    val animalRatingWithNameRDD = sparkSession.sparkContext.parallelize(Seq( (1, "Lion"), (7, "Tiger"),(7, "Fox"), (4, "Leopard")))
    
		//print(numbers.mkString(","))
		//printNumbers(numbersRDD, ",")
		//1. map - count tennis champions victory
    val maptennisChampionsRDD = tennisChampions2018To2021RDD.map(f => (f, 1))    
    		    printTuplesStringIntType(maptennisChampionsRDD, ",", "1.1. Map function example")
    val mapReducedRDD = maptennisChampionsRDD.reduceByKey(_+_)
    		printTuplesStringIntType(mapReducedRDD, ",", "1.2. Map function example - after applying ReduceByKey - wider transfermation")
    
    //2. flatmap
    val flatMappTennisChampionsRDD = tennisChampions2018To2021RDD.flatMap(f => f.split(" ")) //single line input but multiple result
    printStringArrayType(flatMappTennisChampionsRDD, "", "2. FlatMap function example.single line input but multiple result.")
        
    //3. mapPartition
    //mapPartitions has to have a return type of Iterator[U]
    //example 1
    //val mapPartitionRDD = tennisChampions2018To2021RDD.mapPartitions(f =>  List(f.next).iterator)
    //example 2
     val mapPartitionRDD = tennisChampions2018To2021RDD.mapPartitions(f =>   f.map(f => (f,1)))
     printTuplesStringIntType(mapPartitionRDD, ",", "3. mapPartition function example")
     //another example with function call : https://data-flair.training/forums/topic/explain-the-mappartitions-and-mappartitionswithindex/
     val mapPartitionNumber  = numbersRDD.mapPartitions(sumfuncpartition)
     printIntArrayType(mapPartitionNumber, "", "3. mapPartition function example - with function ")
     
     
		//4. mapPartitionWithIndex
    //https://vinaynotes.wordpress.com/2019/02/07/mappartitions-withindex-example-in-spark/
		val mapPartitionWithIndexRDD = tennisChampions2018To2021RDD.mapPartitionsWithIndex( (index, iterator) => iterator.map( f =>{
			//println(index)
			(f, index)} )) 
     printTuplesStringIntType(mapPartitionWithIndexRDD, ",", "4. mapPartitionWithIndex function example")
     
		//5. filter
		val tennisChampionsExcludingRafelNadal = tennisChampions2018To2021RDD.filter(f => f != "Rafael Nadal")
		printStringArrayType(tennisChampionsExcludingRafelNadal, ",", "5. filter function example")
		
		//6. union
		val unionOfAnimalAndFlowerRatingsRDD = flowerRatingWithNameRDD.union(animalRatingWithNameRDD)
		printTuplesStringIntType2(unionOfAnimalAndFlowerRatingsRDD, "", "6. union function example")

		//7. Sample
		val tennisChampionsSample = tennisChampions2018To2021RDD.sample(true, 0.3, 2)
    printStringArrayType(tennisChampionsSample, ",", "7. sample function example")
  	
  }
}