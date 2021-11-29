package transformation

import org.apache.spark.sql.DataFrame
import org.apache.spark.sql.functions._

class DateTransformationRuleImpl extends TransformationRule{
  def applyTransformation(transformInDataframe: DataFrame, columnList: List[String] ): DataFrame ={
   var dataframe = transformInDataframe
  	columnList.foreach(columntoreplace => {
    	//TODO:fix the timestamp issue
  		//dataframe = dataframe.withColumn(columntoreplace,   to_timestamp(col(columntoreplace)))
    })
    
    dataframe
  }
}

object DateTransformationRuleImpl{
	def apply()={
		new DateTransformationRuleImpl()
	}
}