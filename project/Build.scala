import sbt._
import Keys._

object ScalaJsonDemoBuild extends Build {
  lazy val root = Project(id = "ScalaJsonDemo",
    base = file("."),
    settings = Seq(
      libraryDependencies ++= Seq(
        "org.specs2" %% "specs2" % "1.12.3" % "test",
        "io.spray" %% "spray-json" % "1.3.1",
        "net.liftweb" %% "lift-json" % "2.6.2",
        "net.liftweb" %% "lift-json-ext" % "2.6.2"
      )
    )
  )
}
