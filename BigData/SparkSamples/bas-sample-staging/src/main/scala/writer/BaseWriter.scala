package writer

/**
 * @author Hencil Peter
 * 
 */


trait BaseWriter {
  def execute(sourceFilePath: String, targetHDFSPath: String): Boolean  
} 