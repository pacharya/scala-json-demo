package org.pachuworks.JsonReader

import org.pachuworks.model._

import scala.util.parsing.json._

trait NativeJSONReader extends JSONReader[Programmer] {

  def readJSON(filePath: String): Option[JSONType] = {
    read(filePath).flatMap(JSON.parseRaw _)
  }

  def readEntities(filePath: String): List[Programmer] = {
    readJSON(filePath) match {
      case Some(jArray) if jArray.isInstanceOf[JSONArray] => {
        val arr = jArray.asInstanceOf[JSONArray]
        val innerList = arr.list.map(_.asInstanceOf[JSONType])
        innerList.map(createProgrammer _).collect {
          case Some(programmer) => programmer
        }
      }
      case Some(jObj) if jObj.isInstanceOf[JSONObject] => {
        createProgrammer(jObj) match {
          case Some(programmer: Programmer) => List(programmer)
          case None => Nil
        }
      }
      case None => Nil
    }
  }

  private def createProgrammer(jType: JSONType): Option[Programmer] = {
    try {
      val jObj = jType.asInstanceOf[JSONObject]
      val innerMap = jObj.obj
      val nameOpt: Option[String] = innerMap.get("name") match {
        case Some(theName) => {
          Some(theName.asInstanceOf[String])
        }
        case None => {
          println("name not found in JSON: " + jType.toString())
          None
        }
      }
      val langOpt: Option[List[String]] = innerMap.get("languages") match {
        case Some(jArrTypeObj) => {
          val jArr: JSONArray = jArrTypeObj.asInstanceOf[JSONArray]
          Some(jArr.list.map(_.asInstanceOf[String]))
        }
        case None => {
          println("language not found in JSON: " + jType.toString())
          None
        }
      }
      (nameOpt, langOpt) match {
        case (Some(name), Some(languages)) => Some(Programmer(name, languages))
        case _ => None
      }
    }
    catch {
      case ccE: ClassCastException => {
        println("Did not get a JSONObject or got a JSONObject not matching spec")
        None
      }
    }
  }

}

class NativeJSONReaderImpl extends NativeJSONReader {
  def read(filePath: String) = readFile(filePath)
}

class TestNativeJSONReaderImpl extends NativeJSONReader {
  def read(resourceName: String) = readResource(resourceName)
}