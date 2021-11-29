package util

trait StageFolderUtil {
  def execute(sourceFilePath:String, targetHDFSPath: String):Boolean 
}