package org.pachuworks.JsonReader

import org.pachuworks.model._

import net.liftweb.json._

trait LiftJSONReader extends JSONReader[Programmer] {

  def readEntities(filePath: String): List[Programmer] = {
    implicit val formats = DefaultFormats
    read(filePath) match {
      case Some(rawJSON) => {
        val json = parse(rawJSON)
        json.extract[List[Programmer]]
      }
      case None => Nil
    }
  }

}

class LiftJSONReaderImpl extends LiftJSONReader {
  def read(filePath: String) = readFile(filePath)
}

class TestLiftJSONReaderImpl extends LiftJSONReader {
  def read(resourceName: String) = readResource(resourceName)
}