/**
  * @author Hencil Peter
  *
  */

package transformation
import org.apache.spark.sql.DataFrame

trait TransformationRule {
	def applyTransformation(transformInDataframe: DataFrame, columnList: List[String]): DataFrame
}