package org.pachuworks.FileReader

import spray.json._
  import DefaultJsonProtocol._

import org.pachuworks.model._

object MyJsonProtocol extends DefaultJsonProtocol {
  implicit object ProgrammerJsonFormat extends RootJsonFormat[Programmer] {

    def write(p: Programmer) = {
      val langVector : Vector[JsValue] = p.languages.map(JsString(_)).toVector
      JsObject(
        "name" -> JsString(p.name),
        "languages" -> JsArray(langVector)
      )
    }

    def read(value: JsValue) = {
      value.asJsObject.getFields("name","languages") match {
        case Seq(JsString(name), JsArray(langVector)) => {
          val langList : List[String] = langVector.toList.map(_.toString())
          Programmer(name, langList)
        }
      }
    }

  }
}

import MyJsonProtocol._

trait SprayJSONReader extends JSONReader[Programmer] {

  def readEntities(filePath: String) : List[Programmer] = {
    read(filePath) match {
      case Some(rawJSON) => {
        JsonParser(rawJSON) match {
          case JsArray(programmersVector) => {
            programmersVector.toList.map(_.convertTo[Programmer])
          }
          case _ => {
            Nil
          }
        }
      }
      case None => Nil
    }
  }

}

class SprayJSONReaderImpl extends SprayJSONReader {
  def read(filePath: String) = readFile(filePath)
}

class TestSprayJSONReaderImpl extends SprayJSONReader {
  def read(resourceName: String) = readResource(resourceName)
}

