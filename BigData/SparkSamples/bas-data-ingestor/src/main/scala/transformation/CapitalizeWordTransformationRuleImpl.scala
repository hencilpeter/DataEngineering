/**
  * @author Hencil Peter
  *
  */

package transformation
import org.apache.spark.sql.functions.{ initcap, col }
import org.apache.spark.sql.DataFrame

class CapitalizeWordTransformationRuleImpl extends TransformationRule {

	def applyTransformation(transformInDataframe: DataFrame, columnList: List[String]): DataFrame = {
    if (transformInDataframe.isEmpty)
    	return transformInDataframe
    	
		var dataframe = transformInDataframe
		columnList.foreach(columntoreplace => {
			dataframe = dataframe.withColumn(columntoreplace, initcap(col(columntoreplace)))
		})

		dataframe
	}
}

object CapitalizeWordTransformationRuleImpl {
	def apply() = {
		new CapitalizeWordTransformationRuleImpl()
	}
}