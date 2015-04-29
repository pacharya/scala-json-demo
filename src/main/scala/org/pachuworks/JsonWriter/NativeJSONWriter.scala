package org.pachuworks.JsonWriter

import org.pachuworks.model._

import scala.util.parsing.json._

class NativeJSONWriter extends JSONWriter[Programmer] {

  private def toJson(p: Programmer) : String = {
    val progJSONObj : JSONObject =
      JSONObject(Map(
        "name" -> p.name,
        "languages" -> JSONArray(p.languages)
      )
    )
    progJSONObj.toString()
  }

  def writeEntities(entities: List[Programmer]) : String = {
    val entitiesJSON : List[String] = entities.map(toJson _)
    def foldFn(acc: String, v: String) : String = {
      if (acc == "[") {
        acc + v
      }
      else {
        acc + "," + v
      }
    }
    entitiesJSON.foldLeft[String]("[")(foldFn) + "]"
  }

}