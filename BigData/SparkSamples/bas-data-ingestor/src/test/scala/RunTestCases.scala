/**
  * @author Hencil Peter
  *
  */

import testtransformation.{CapitalizeWordTransformationRuleImplTest, NumberTransformationRuleImplTest}
import org.scalatest._

object RunTestCases extends App {
  org.scalatest.run(new CapitalizeWordTransformationRuleImplTest())
  org.scalatest.run(new NumberTransformationRuleImplTest())
}