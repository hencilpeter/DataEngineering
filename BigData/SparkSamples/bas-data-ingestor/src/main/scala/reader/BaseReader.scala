package reader
import org.apache.spark.sql.DataFrame

trait BaseReader {
   def execute(filePath: String): DataFrame
}