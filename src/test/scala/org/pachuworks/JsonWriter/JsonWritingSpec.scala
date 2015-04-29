package org.pachuworks.JsonWriter

import org.specs2.matcher._
import org.specs2.mutable._

import org.pachuworks.model._

class JsonWritingSpec extends Specification with ThrownExpectations {

  val nativeJsonWriter = new NativeJSONWriter()

  val testInput: List[Programmer] = List(
    Programmer("Andrew", List("Javascript")),
    Programmer("Anil", List("Java","XML"))
  )

  val testOutput : String =
    """[{"name":"Andrew","languages":["Javascript"]},{"name":"Anil","languages":["Java","XML"]}]"""

  val regex = "\\s+".r

  def expectedJSON(left: String) : Boolean = {
    val retValue = (regex.replaceAllIn(left,"") == testOutput)
    if (retValue == false) {
      println("Output JSON: '" + left + "'")
    }
    retValue
  }

  "Writer classes" should {
    "NativeJSONWriter write out JSON" in {
      val jsonStr = nativeJsonWriter.writeEntities(testInput)
      expectedJSON(jsonStr) must beTrue
    }
  }

}