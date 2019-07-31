package scala.tools.jardiff

import java.nio.file.{Files, Path}

import scala.tools.scalap.scalax.rules.ScalaSigParserError
import scala.tools.scalap.scalax.rules.scalasig.{ByteCode, ScalaSigAttributeParsers}

class ScalapSigRenderer() extends FileRenderer {
  def outFileExtension: String = ".scalap"
  override def render(in: Path, out: Path): Unit = {
    val classBytes = Files.readAllBytes(in)
    try {
      val scalaSig = ScalaSigAttributeParsers.parse(ByteCode(classBytes))
      val decompiled = scala.tools.scalap.Main.parseScalaSignature(scalaSig, in.getFileName.toString == "package.sig")
      if (decompiled != "") {
        Files.createDirectories(out.getParent)
        Files.write(out, decompiled.getBytes("UTF-8"))
      }
    } catch {
      case err: ScalaSigParserError =>
        System.err.println("WARN: unable to invoke scalap on: " + in + ": " + err.getMessage)
    }
  }
}
