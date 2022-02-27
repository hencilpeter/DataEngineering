package spark_rdd

import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.types._
import org.apache.spark.rdd._
//import org.apache.spark.sql.types._
//import org.apache.spark.sql.expressions._

import org.apache.spark.sql.catalyst.ScalaReflection.schemaFor
import scala.reflect.runtime.universe._

object WiderTransformations {

	//https://stackoverflow.com/questions/41651817/scala-generic-function-to-convert-any-type-to-specified-generic-type-argument
	//	def printV[T:ClassTag](rddResult:Any, description:String):Unit={
	//		var test
	//		T match {
	//			case rddResult : RDD[(Char, String, String, Int)] => RDD[(Char, String, String, Int)](rddResult)
	//		}
	//		println(description)
	//
	//		println("----------------------------------------------------")
	//	}

	//	def genFunction[T: TypeTag](value: Any)={
	//		val dataTypeName = schemaFor[T]dataType
	//
	//		dataTypeName match {
	//			case StringType => println("String Type.....")
	//			case _ => println("Unknown type.... : " + dataTypeName)
	//
	//		}
	//
	//	}

	//TODO:Wrte a generic function
	def printRDDTuplesV1(rddValue: RDD[(Char, String, String, Int)], description: String) = {
		println("***********************************************")
		println(description)
		println(rddValue.collect().mkString("\n"))
		println("-----------------------------------------------")
	}

	def printRDDTuplesV2(rddValue: RDD[(String, Int)], description: String) = {
		println("***********************************************")
		println(description)
		println(rddValue.collect().mkString("\n"))
		println("-----------------------------------------------")
	}

	def printRDDTuplesV3(rddValue: RDD[(String, Iterable[String])], description: String) = {
		println("***********************************************")
		println(description)
		println(rddValue.collect().mkString("\n"))
		println("-----------------------------------------------")
	}

	def printRDDTuplesV4(rddValue: RDD[(Int, Int)], description: String) = {
		println("***********************************************")
		println(description)
		println(rddValue.collect().mkString("\n"))
		println("-----------------------------------------------")
	}

	def printRDDTuplesStringInt(rddValue: RDD[(String, Int)], description: String) = {
		println("***********************************************")
		println(description)
		println(rddValue.collect().mkString("\n"))
		println("-----------------------------------------------")
	}

	def printRDDTuplesStringString(rddValue: RDD[(String, String)], description: String) = {
		println("***********************************************")
		println(description)
		println(rddValue.collect().mkString("\n"))
		println("-----------------------------------------------")
	}

	def genFunction[T: TypeTag](value: T) = {
		val dataTypeName = schemaFor[T].dataType

		dataTypeName match {
			case StringType => println("String Type.....")
			case _ => println("Unknown type.... : " + dataTypeName)

		}

	}

	//https://stackoverflow.com/questions/46348902/check-data-type-in-spark
	def genFunction2[T <: Product: TypeTag](anyRDD: T) = {

		println(anyRDD.getClass())
		//anyRDD.foreach(println)
		//val newValue = SparkSession.builder().appName("WiderTransformations").master("local[*]").getOrCreate().sparkContext.parallelize(anyRDD)
		//		val dataTypeName = schemaFor[T].dataType
		//
		//		dataTypeName match {
		//			case StringType => println("String Type.....")
		//			case _ => println("Unknown type.... : " + dataTypeName)
		//
		//		}

		println("test..." + TypeTag.getClass())
		//   }
		//
		//			anyRDD match {
		//				case _ => println(anyRDD.ty
		//			}
	}

	def main(args: Array[String]): Unit = {

		val sparkSession: SparkSession = SparkSession.builder().appName("WiderTransformations").master("local[*]").getOrCreate()
		sparkSession.sparkContext.setLogLevel("ERROR")

		val creditScoreAndName2020 = sparkSession.sparkContext.parallelize(Seq(('A', "Balu", "India", 1910), ('C', "Tom", "England", 1720),
			('A', "Berner", "America", 1921), ('B', "Angela", "Ukraine", 1825),
			('C', "Putin", "Russia", 1720), ('A', "David", "England", 1912),
			('C', "Putin", "Russia", 1720)))

		val creditScoreAndName2021 = sparkSession.sparkContext.parallelize(Seq(('A', "Balu", "India", 1912), ('D', "Tom", "England", 1620),
			('A', "Berner", "America", 1921), ('D', "Angela", "Ukraine", 1630),
			('C', "Putin", "Russia", 1720), ('A', "David", "England", 1912)))

		val countryAndName1 = sparkSession.sparkContext.parallelize(Seq(("India", "Balu"), ("Singapore", "Jeffrey"), ("India", "Chandradip"),
			("Australia", "John"), ("Pakisthan", "Imrankan"), ("Srilanka", "Rajapakshe"),
			("Malaysia", "Somu")))

		val countryAndName2 = sparkSession.sparkContext.parallelize(Seq(
			("India", "Balu"),
			("England", "Elesabath"), ("France", "Emmanuel"), ("Italy", "Damiano"),
			("Germany", "Jens"), ("England", "Paul")))

		val keyValue1 = sparkSession.sparkContext.parallelize(Seq(("John", 200), ("Balu", 300), ("Priyan", 250), ("Raj", 229), ("Jegan", 233)))
		val keyValue2 = sparkSession.sparkContext.parallelize(Seq(("Mary", 100), ("Sweety", 300), ("Priyan", 200), ("Raj", 100), ("Rosely", 203)))

		val numRDD1 = sparkSession.sparkContext.parallelize(Seq(1, 2, 3, 4, 5))
		val numRDD2 = sparkSession.sparkContext.parallelize(Seq(11, 22, 33, 44, 55))

		//1. Intersection
		val unionCreditScore = creditScoreAndName2020.intersection(creditScoreAndName2021)
		printRDDTuplesV1(creditScoreAndName2020, "1.0. Intersection example - creditScoreAndName2020")
		printRDDTuplesV1(creditScoreAndName2021, "1.0. Intersection example - creditScoreAndName2021")
		printRDDTuplesV1(unionCreditScore, "1. Intersection example")

		//2. Distinct
		val distinctCreditScore = creditScoreAndName2020.distinct()
		printRDDTuplesV1(distinctCreditScore, "2. Distinct example")

		//3. Join
		val joinCountryAndNameRDD = countryAndName1.join(countryAndName2)
		printRDDTuplesV1(distinctCreditScore, "3. Join example (countryAndName1.countryAndName2)")
		println("--------")
		joinCountryAndNameRDD.foreach(println)

		//4. ReduceByKey

		val reduceByKeyResult = keyValue1.reduceByKey((x, y) => x + y)
		printRDDTuplesStringInt(reduceByKeyResult, "4.1. ReduceByKey example : KeyValue")

		val reduceByKeyCountry = countryAndName1.reduceByKey((x, y) => x + y)
		printRDDTuplesStringString(reduceByKeyCountry, "4.2. ReduceByKey example : CountryAndName")

		//5. GroupByKey
		val groupByResult = countryAndName1.groupByKey()
		printRDDTuplesV3(groupByResult, "5. GroupByKey example : countryAndName1")

		//6. sortBy and sortyByKey
		//val sortByKeyResult = keyValue1.sortBy(f => f[0] , ascending=true, numPartitions=4)
		val sortByResult = keyValue1.sortBy(line => line._1, ascending = true, numPartitions = 4)
		printRDDTuplesStringInt(sortByResult, "6.1 SortBy Sample Result....")

		val sortByKeyResult = keyValue1.sortByKey(ascending = true, numPartitions = 4)
		printRDDTuplesStringInt(sortByKeyResult, "6.2 sortByKey Sample Result. Number of partitions : " + sortByKeyResult.getNumPartitions)

		//7. Cartesian
		val cartesianResultRDD = numRDD1.cartesian(numRDD2)
		printRDDTuplesV4(cartesianResultRDD, "7. Cartesian example")

		//8.Repartition
		println("Repartition example..")
		println(s"Default number of partitions : ${numRDD2.getNumPartitions}")
		val defaultResult = numRDD2.mapPartitionsWithIndex((index, iterValue) => iterValue.map(f => (index, f)))
		defaultResult.foreach(println)

		val repartitionRDD = numRDD2.repartition(3)
		println("Repartition(3) : " + repartitionRDD.getNumPartitions)
		val repartitionResult = repartitionRDD.mapPartitionsWithIndex((index, iterValue) => iterValue.map(f => (index, f)))
		repartitionResult.foreach(println)

		//9.Coalesce
		println("**************************************************")
		println("Coalesce example..")
		println(s"Default number of partitions : ${numRDD2.getNumPartitions}")
		val CoalesceDefaultResult = numRDD2.mapPartitionsWithIndex((index, iterValue) => iterValue.map(f => (index, f)))
		CoalesceDefaultResult.foreach(println)

		val rcoalesceRDD = numRDD2.coalesce(2)
		println("coalesce(2) : " + rcoalesceRDD.getNumPartitions)
		val coalesceRDDResult = rcoalesceRDD.mapPartitionsWithIndex((index, iterValue) => iterValue.map(f => (index, f)))
		coalesceRDDResult.foreach(println)

	}

}