/**
  * @author Hencil Peter
  *
  */

package transformation

trait TransformationRuleExtractor {
  def extractRules(fileId: Int) : Map[TransformationRule, List[String]]
}