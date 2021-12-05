/**
  * @author Hencil Peter
  *
  */

package transformation

class TransformationRuleExtractorImpl extends TransformationRuleExtractor{
  def extractRules(fileId: Int) : Map[TransformationRule, List[String]]={
  	var ruleList : Map[TransformationRule,List[String]] = Map()
  	
  	//TODO: below logic to be replaced. all the configuration will be read from the config tables.
  	if (fileId == 1)
  		ruleList = Map(CapitalizeWordTransformationRuleImpl() -> List("Country", "AccountNumber", "Region"), 
  				NumberTransformationRuleImpl() -> List("BalanceAmount"))
  	else if (fileId ==2) 
  		ruleList = Map(CapitalizeWordTransformationRuleImpl() -> List("Region"))
  				
  	ruleList
  }
}

object TransformationRuleExtractorImpl{
	def apply()={
		new TransformationRuleExtractorImpl()
	}
}