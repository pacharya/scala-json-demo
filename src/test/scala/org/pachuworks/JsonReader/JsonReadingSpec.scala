package org.pachuworks.JsonReader

import org.specs2.matcher._
import org.specs2.mutable._

class JsonReadingSpec extends Specification with ThrownExpectations {

  val nativeJsonReader = new TestNativeJSONReaderImpl()
  val sprayJsonReader = new TestSprayJSONReaderImpl()
  val liftJsonReader = new TestLiftJSONReaderImpl()

  "Reader classes" should {
    "NativeJSONReader Read in JSON" in {
      val programmers = nativeJsonReader.readEntities("foo.json")
      programmers.size must_== 3
    }
    "SprayJSONReader Read in JSON" in {
      val programmers = sprayJsonReader.readEntities("foo.json")
      programmers.size must_== 3
    }
    "LiftJSONReader Read in JSON" in {
      val programmers = liftJsonReader.readEntities("foo.json")
      programmers.size must_== 3
    }
  }

}