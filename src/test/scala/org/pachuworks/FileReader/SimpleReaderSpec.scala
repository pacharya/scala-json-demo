package org.pachuworks.FileReader

import org.specs2.matcher._
import org.specs2.mutable._

class SimpleReaderSpec extends Specification with ThrownExpectations {

  val simpleReader = new TestReaderImpl()

  "SimpleReader" should {
    "read in a file" in {
      simpleReader.read("foo") must_== Some("abcd\n")
      simpleReader.read("bar") must beNone
    }
  }

}