/**
  * @author Hencil Peter
  *
  */

package transformation
import org.apache.spark.sql.DataFrame

trait TransformationRuleEngine {
	def applyTransformation(dataframe: DataFrame): DataFrame
}