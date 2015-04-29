package org.pachuworks.JsonWriter

import org.specs2.matcher._
import org.specs2.mutable._

import org.pachuworks.model._

class JsonWritingSpec extends Specification with ThrownExpectations {

  val nativeJsonWriter = new NativeJSONWriter()
  val liftJsonWriter = new LiftJSONWriter()

  val testInput: List[Programmer] = List(
    Programmer("Andrew", List("Javascript")),
    Programmer("Anil", List("Java","XML"))
  )

  val testOutput : String =
    """[{"name":"Andrew","languages":["Javascript"]},{"name":"Anil","languages":["Java","XML"]}]"""

  val regex = "\\s+".r

  def testJSONWriter(jsonWriter: JSONWriter[Programmer]) : Boolean = {
    val json = jsonWriter.writeEntities(testInput)
    val retValue = (regex.replaceAllIn(json,"") == testOutput)
    if (retValue == false) {
      println("Output JSON: '" + json + "'")
    }
    retValue
  }

  "Writer classes" should {
    "NativeJSONWriter write out JSON" in {
      testJSONWriter(nativeJsonWriter) must beTrue
    }
    "LiftJSONWriter write out JSON" in {
      testJSONWriter(liftJsonWriter) must beTrue
    }
  }

}