/**
  * @author Hencil Peter
  *
  */

package transformation

import org.apache.spark.sql.{ DataFrame }

class TransformationRuleEngineImpl extends TransformationRuleEngine {

	def applyTransformation(dataframe: DataFrame, fileId: Int, tranformationRuleExtractor: TransformationRuleExtractor): DataFrame = {

		if (dataframe.isEmpty)
			return dataframe

		//get rule map for the file
		val transformationRuleMap = tranformationRuleExtractor.extractRules(fileId) 

		if (transformationRuleMap.size == 0)
			return dataframe

		//apply rules
		var transformedDataframe = dataframe
		transformationRuleMap.foreach {
			ruleMap =>
				transformedDataframe = ruleMap._1.applyTransformation(transformedDataframe, ruleMap._2)
		}

		transformedDataframe
	}
	
}

object TransformationRuleEngineImpl {
	def apply() = {
		new TransformationRuleEngineImpl()
	}
}