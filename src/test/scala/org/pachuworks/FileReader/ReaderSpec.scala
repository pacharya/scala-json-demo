package org.pachuworks.FileReader

import org.specs2.matcher._
import org.specs2.mutable._

class ReaderSpec extends Specification with ThrownExpectations {

  val simpleReader = new TestReaderImpl()
  val nativeJsonReader = new TestNativeJSONReaderImpl()
  val sprayJsonReader = new TestSprayJSONReaderImpl()
  val liftJsonReader = new TestLiftJSONReaderImpl()

  "Reader classes" should {
    "SimpleReader read in a file" in {
      simpleReader.read("foo") must_== Some("abcd\n")
      simpleReader.read("bar") must beNone
    }
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