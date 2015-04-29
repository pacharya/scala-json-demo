package org.pachuworks.JsonWriter

import net.liftweb.json._

import org.pachuworks.model.Programmer

class LiftJSONWriter extends JSONWriter[Programmer] {

  def writeEntities(entities: List[Programmer]) : String = {
    implicit val formats = DefaultFormats
    val entitiesJVals = Extraction.decompose(entities)
    compact(render(entitiesJVals))
  }

}
