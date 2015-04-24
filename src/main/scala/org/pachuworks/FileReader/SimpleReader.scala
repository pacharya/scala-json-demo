package org.pachuworks.FileReader

trait SimpleReader {

  def read(filePath: String) : Option[String]

  def readFile(filePath: String) : Option[String] = {
    try {
      val fileContents = scala.io.Source.fromFile(filePath).mkString
      Some(fileContents)
    }
    catch {
      case ex: Exception => {
        println("Caught Exception: " + ex)
        None
      }
    }
  }

  def readResource(resourceName: String) : Option[String] = {
    try {
      val fileContents = scala.io.Source.fromURL(getClass.getResource("/" + resourceName)).mkString
      Some(fileContents)
    }
    catch {
      case ex: Exception => {
        println("Caught Exception: " + ex)
        None
      }
    }
  }

}

class SimpleReaderImpl extends SimpleReader {
  def read(filePath: String) = readFile(filePath)
}

class TestReaderImpl extends SimpleReader {
  def read(resourceName: String) = readResource(resourceName)
}