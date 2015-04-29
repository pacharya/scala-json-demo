package org.pachuworks.JsonWriter

trait JSONWriter[T] {

  def writeEntities(entities: List[T]): String

}