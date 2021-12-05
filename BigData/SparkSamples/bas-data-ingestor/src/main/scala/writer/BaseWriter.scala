/**
  * @author Hencil Peter
  *
  */

package writer

trait BaseWriter {
	def execute(sourceFilePath: String, targetHDFSPath: String): Boolean
} 