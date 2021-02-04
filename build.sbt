name := "2021-february"

version := "0.1"

scalaVersion := "2.13.4"

lazy val libraries = List(
  "org.typelevel" %% "cats-core" % "2.3.1",
  "org.scalameta" %% "munit" % "0.7.21" % Test
)

lazy val branchWars = (project in file(".")).settings(
  libraryDependencies ++= libraries,
  testFrameworks += new TestFramework("munit.Framework")
)