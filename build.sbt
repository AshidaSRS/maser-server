name := "maser"

version := "0.0.1"

organization := "com.shin"

scalaVersion := "2.12.2"
ensimeScalaVersion in ThisBuild := "2.12.2"

crossScalaVersions := Seq("2.11.11", scalaVersion.value)

libraryDependencies ++= Dependencies.core

testOptions += Tests.Argument(TestFrameworks.JUnit, "-v")
