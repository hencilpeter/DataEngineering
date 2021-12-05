/**
  * @author Hencil Peter
  *
  */

package testtransformation

import transformation.NumberTransformationRuleImpl
import connection.SparkConnectionManager
import org.apache.spark.sql.DataFrame

import org.scalatest._
import org.apache.spark.sql.SparkSession

class NumberTransformationRuleImplTest extends FunSuite with BeforeAndAfter {

	val transformationColumnList: List[String] = List("balanceNumeric", "balanceDecimal")
	var testDataFrame: org.apache.spark.sql.DataFrame = null

	before {
		//common/generic initialization code
		testDataFrame = SparkConnectionManager.GetSparkSession().emptyDataFrame
	}

	after {
		//common object clean-up code
	}

	test("An Empty Unit Test Case. Just for Demo.") {
		//Arrange-Act-Assert pattern

		//Arrange
		val expected = 1; //initialization

		//Act
		val actual = 1; //actual function call

		//Assert
		assert(actual === expected)
	}

	test("Test Number Transformation. Dataframe with no rows") {
		//Arrange
		//empty dataframe. initialized inside before{}.

		//Act
		val actualResult = NumberTransformationRuleImpl().applyTransformation(testDataFrame, transformationColumnList)

		//Assert
		assert(actualResult.isEmpty === true)
	}

	test("Test Number Transformation. Dataframe with one valid row.") {
		//Arrange
		val testColumns = Seq("accountId", "balanceNumeric", "balanceDecimal");
		val testData = Seq(("1", "2", ".2"));
		testDataFrame = SparkConnectionManager.GetSparkSession().createDataFrame(testData).toDF(testColumns: _*)

		//Act
		val actualResult = NumberTransformationRuleImpl().applyTransformation(testDataFrame, transformationColumnList)

		//Assert
		val actualRow1BalanceNumericValue = actualResult.select("balanceNumeric").where("accountId==1").collectAsList().get(0).mkString
		assert("2.000000000" === actualRow1BalanceNumericValue)

		val actualRow1balanceDecimalValue = actualResult.select("balanceDecimal").where("accountId==1").collectAsList().get(0).mkString
		assert("0.200000000" === actualRow1balanceDecimalValue)

	}

	test("Test Number Transformation. Dataframe with two valid rows.") {
		//Arrange
		val testColumns = Seq("accountId", "balanceNumeric", "balanceDecimal");
		val testData = Seq(("1", "200", "123.2"), ("2", "10000", "567.2123"));
		testDataFrame = SparkConnectionManager.GetSparkSession().createDataFrame(testData).toDF(testColumns: _*)

		//Act
		val actualResult = NumberTransformationRuleImpl().applyTransformation(testDataFrame, transformationColumnList)

		//Assert
		val actualRow1BalanceNumericValue = actualResult.select("balanceNumeric").where("accountId==1").collectAsList().get(0).mkString
		assert("200.000000000" === actualRow1BalanceNumericValue)

		val actualRow2BalanceNumericValue = actualResult.select("balanceNumeric").where("accountId==2").collectAsList().get(0).mkString
		assert("10000.000000000" === actualRow2BalanceNumericValue)

		val actualRow1balanceDecimalValue = actualResult.select("balanceDecimal").where("accountId==1").collectAsList().get(0).mkString
		assert("123.200000000" === actualRow1balanceDecimalValue)

		val actualRow2balanceDecimalValue = actualResult.select("balanceDecimal").where("accountId==2").collectAsList().get(0).mkString
		assert("567.212300000" === actualRow2balanceDecimalValue)
	}

}

