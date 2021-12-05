/**
  * @author Hencil Peter
  *
  */

package util

trait CoreWriteUtil {
	def execute(stagedHDFSPath: String, coreHDFSPath: String): Boolean
}