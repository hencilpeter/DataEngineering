/**
  * @author Hencil Peter
  *
  */

package enumeration

object TransformationType extends Enumeration {
	type TransformationType = Value

	val CapitalizeWordTransfermation = Value("1")
	val DateTransfermation = Value("2")
	val NumberTransfermation = Value("3")

}