package org.pachuworks.FileReader

trait JSONReader[T] extends SimpleReader {

  def readEntities(filePath: String) : List[T]

}