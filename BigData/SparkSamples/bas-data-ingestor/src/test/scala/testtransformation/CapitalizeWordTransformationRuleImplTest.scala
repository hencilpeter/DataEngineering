/**
  * @author Hencil Peter
  *
  */


package testtransformation
import org.scalatest._
import connection.SparkConnectionManager
import org.apache.spark.sql.{ DataFrame }
import org.apache.spark.sql.functions.{ col }
import transformation.{ CapitalizeWordTransformationRuleImpl }

class CapitalizeWordTransformationRuleImplTest extends FunSuite with BeforeAndAfter {

	val transformColumnList: List[String] = List("countryName", "description")
	var testDataframe: org.apache.spark.sql.DataFrame = null

	before {
		//initialize dataframe with empty object
		testDataframe = SparkConnectionManager.GetSparkSession().emptyDataFrame
	}

	after {
		//add object clean-up code here...
	}

	test("Capitalize Word Transformation Test. Empty Data Frame.") {
		//Arrange

		//Act
		val actualResult = CapitalizeWordTransformationRuleImpl().applyTransformation(testDataframe, transformColumnList)

		//Assert
		assert(actualResult.isEmpty === true)
	}

	test("Capitalize Word Transformation Test. Dataframe with single row but all capital letters.") {
		//Arrange
		val testColumns = Seq("countryCode", "countryName", "description")
		val testData = Seq(("SIN", "SINGAPORE", "SINGAPORE IS A SUNNY, TROPICAL ISLAND IN SOUTHEAST ASIA"))
		testDataframe = SparkConnectionManager.GetSparkSession().createDataFrame(testData).toDF(testColumns: _*)

		//Act
		val actualResult = CapitalizeWordTransformationRuleImpl().applyTransformation(testDataframe, transformColumnList)

		//Assert
		val actualCountryName = actualResult.select("countryName").where("countryCode = 'SIN'").collectAsList().get(0).mkString
		assert(actualCountryName.equals("Singapore"))

		val actualDescription = actualResult.select("description").where("countryCode = 'SIN'").collectAsList().get(0).mkString
		assert(actualDescription.equals("Singapore Is A Sunny, Tropical Island In Southeast Asia"))
	}

	test("Capitalize Word Transformation Test. Dataframe with single row but all small letters.") {
		//Arrange
		val testColumns = Seq("countryCode", "countryName", "description")
		val testData = Seq(("SIN", "singapore", "singapore is a sunny, tropical island in southeast asia"))
		val testDataframe = SparkConnectionManager.GetSparkSession().createDataFrame(testData).toDF(testColumns: _*)

		//Act
		val actualResult = CapitalizeWordTransformationRuleImpl().applyTransformation(testDataframe, transformColumnList)

		//Assert
		val actualCountryName = actualResult.select("countryName").where("countryCode = 'SIN'").collectAsList().get(0).mkString
		assert(actualCountryName.equals("Singapore"))

		val actualDescription = actualResult.select("description").where("countryCode = 'SIN'").collectAsList().get(0).mkString
		assert(actualDescription.equals("Singapore Is A Sunny, Tropical Island In Southeast Asia"))
	}

	test("Capitalize Word Transformation Test. Dataframe with single row and each word's first letter is capitalized.") {
		//Arrange
		val testColumns = Seq("countryCode", "countryName", "description")
		val testData = Seq(("SIN", "Singapore", "Singapore Is A Sunny, Tropical Island In Southeast Asia"))
		val testDataframe = SparkConnectionManager.GetSparkSession().createDataFrame(testData).toDF(testColumns: _*)

		//Act
		val actualResult = CapitalizeWordTransformationRuleImpl().applyTransformation(testDataframe, transformColumnList)

		//Assert
		val actualCountryName = actualResult.select("countryName").where("countryCode = 'SIN'").collectAsList().get(0).mkString
		assert(actualCountryName.equals("Singapore"))

		val actualDescription = actualResult.select("description").where("countryCode = 'SIN'").collectAsList().get(0).mkString
		assert(actualDescription.equals("Singapore Is A Sunny, Tropical Island In Southeast Asia"))
	}

}
