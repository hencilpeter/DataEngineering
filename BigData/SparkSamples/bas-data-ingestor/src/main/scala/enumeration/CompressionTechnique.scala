/**
  * @author Hencil Peter
  *
  */

package enumeration

object CompressionTechnique extends Enumeration {
	type CompressionTechnique = Value

	val Snappy = Value("SAPPY")
	val GZip = Value("GZip")
	val LZO = Value("LZO")
	val BZip2 = Value("BZip2")
}