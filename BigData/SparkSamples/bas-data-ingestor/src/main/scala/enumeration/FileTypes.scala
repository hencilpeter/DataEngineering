/**
  * @author Hencil Peter
  *
  */

package enumeration

object FileTypes extends Enumeration {
	type FileTypes = Value

	val TextFile = Value("txt")
	val CSV = Value("csv")
	val JSON = Value("json")
	val Parquet = Value("Parquet")
	val ORC = Value("orc")
	val AVRO = Value("avro")

}