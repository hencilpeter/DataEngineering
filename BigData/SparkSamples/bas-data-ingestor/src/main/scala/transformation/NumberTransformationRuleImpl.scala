package transformation

import org.apache.spark.sql.DataFrame
import org.apache.spark.sql.functions._
import org.apache.parquet.format.DecimalType
import org.apache.spark.sql.types.DecimalType

class NumberTransformationRuleImpl extends TransformationRule{
  def applyTransformation(transformInDataframe: DataFrame, columnList: List[String] ): DataFrame ={
  	 var dataframe = transformInDataframe
  	columnList.foreach(columntoreplace => {
  		//TODO:remove hard-coding 
    	dataframe = dataframe.withColumn(columntoreplace,  col(columntoreplace).cast("decimal(38,9)") )
    })
    
    dataframe
  }
}

object NumberTransformationRuleImpl{
	def apply(): NumberTransformationRuleImpl={
		new NumberTransformationRuleImpl()
	}
}