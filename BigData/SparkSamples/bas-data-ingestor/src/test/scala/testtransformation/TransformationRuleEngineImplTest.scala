/**
  * @author Hencil Peter
  *
  */

package testtransformation

import org.scalatest.FunSuite
import org.scalatest.BeforeAndAfter
import org.mockito.Mockito
import org.mockito.Mockito.when

import transformation.{TransformationRule,
	CapitalizeWordTransformationRuleImpl,
	NumberTransformationRuleImpl,
	TransformationRuleEngineImpl,
	TransformationRuleExtractorImpl
}

import connection.SparkConnectionManager

class TransformationRuleEngineImplTest extends FunSuite with BeforeAndAfter {
	var ruleList: Map[TransformationRule, List[String]] = Map()
	var testColumns: Seq[String] = Seq()

	before {
		ruleList = Map()
		testColumns = Seq("countryCode", "Country", "AccountNumber", "AccountHolderName", "BalanceAmount")
	}

	after {
		//TODO : add clean-up code
	}

	test("Test Transformation Rule Engine. Empty Dataframe test.") {
		//Arrange
		val testData = Seq(("SIN", "singapore", "1001", "tom neufelder", "1000"))
		val testDataframe = SparkConnectionManager.GetSparkSession().emptyDataFrame
		//mock the transformation extractor object
		val mockTransformationRuleExtractorImpl = Mockito.mock(classOf[TransformationRuleExtractorImpl])
		when(mockTransformationRuleExtractorImpl.extractRules(1)).thenReturn(ruleList)

		//Act
		val actualResult = TransformationRuleEngineImpl().applyTransformation(testDataframe, 1, mockTransformationRuleExtractorImpl)

		//Assert
		assert(actualResult.isEmpty === true)
	}

	test("Test Transformation Rule Engine. Capitalize Word Transformation Rule.") {
		//Arrange
		ruleList = Map(
			CapitalizeWordTransformationRuleImpl() -> List("Country", "AccountHolderName"))
		val testData = Seq(("SIN", "singapore", "1001", "tom neufelder", "1000"))
		val testDataframe = SparkConnectionManager.GetSparkSession().createDataFrame(testData).toDF(testColumns: _*)
		//mock the transformation extractor object
		val mockTransformationRuleExtractorImpl = Mockito.mock(classOf[TransformationRuleExtractorImpl])
		when(mockTransformationRuleExtractorImpl.extractRules(1)).thenReturn(ruleList)

		//Act
		val actualResult = TransformationRuleEngineImpl().applyTransformation(testDataframe, 1, mockTransformationRuleExtractorImpl)

		//Assert
		assert(actualResult.first().getAs("Country").equals("Singapore"))
		assert(actualResult.first().getAs("AccountHolderName").equals("Tom Neufelder"))
	}

	test("Test Transformation Rule Engine. Number Transformation Rule.") {
		//Arrange
		ruleList = Map(NumberTransformationRuleImpl() -> List("BalanceAmount"))
		val testData = Seq(("SIN", "singapore", "1001", "tom neufelder", "1000"))
		val testDataframe = SparkConnectionManager.GetSparkSession().createDataFrame(testData).toDF(testColumns: _*)
		//mock the transformation extractor object
		val mockTransformationRuleExtractorImpl = Mockito.mock(classOf[TransformationRuleExtractorImpl])
		when(mockTransformationRuleExtractorImpl.extractRules(1)).thenReturn(ruleList)

		//Act
		val actualResult = TransformationRuleEngineImpl().applyTransformation(testDataframe, 1, mockTransformationRuleExtractorImpl)

		//Assert
		assert(actualResult.first().getAs("BalanceAmount").toString() === "1000.000000000")
	}

	test("Test Transformation Rule Engine. Capitalize Word and Number Transformation Rule.") {
		//Arrange
		ruleList = Map(
			CapitalizeWordTransformationRuleImpl() -> List("Country", "AccountHolderName"),
			NumberTransformationRuleImpl() -> List("BalanceAmount"))
		val testData = Seq(("SIN", "singapore", "1001", "tom neufelder", "1000"))
		val testDataframe = SparkConnectionManager.GetSparkSession().createDataFrame(testData).toDF(testColumns: _*)
		//mock the transformation extractor object
		val mockTransformationRuleExtractorImpl = Mockito.mock(classOf[TransformationRuleExtractorImpl])
		when(mockTransformationRuleExtractorImpl.extractRules(1)).thenReturn(ruleList)

		//Act
		val actualResult = TransformationRuleEngineImpl().applyTransformation(testDataframe, 1, mockTransformationRuleExtractorImpl)

		//Assert
		assert(actualResult.first().getAs("Country").equals("Singapore"))
		assert(actualResult.first().getAs("AccountHolderName").equals("Tom Neufelder"))
		assert(actualResult.first().getAs("BalanceAmount").toString() === "1000.000000000")
	}

}