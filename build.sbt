ThisBuild / version := "0.1.0-SNAPSHOT"

ThisBuild / scalaVersion := "2.13.10"

//libraryDependencies += "org.scalatest" %% "scalatest" % "3.2.11" % Test
libraryDependencies += "org.scalatest" %% "scalatest" % "3.2.9" % "test"


lazy val root = (project in file("."))
  .settings(
    name := "Quantexa_questions"
  )
