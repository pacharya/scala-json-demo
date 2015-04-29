package org.pachuworks.JsonReader

import org.pachuworks.FileReader._

trait JSONReader[T] extends SimpleReader {

  def readEntities(filePath: String): List[T]

}