name := "maser"

version := "0.0.1"

organization := "com.shin"

scalaVersion := "2.12.4"
ensimeScalaVersion in ThisBuild := "2.12.4"

addCompilerPlugin("org.scalameta" % "paradise" % "3.0.0-M10" cross CrossVersion.full)

crossScalaVersions := Seq("2.11.11", scalaVersion.value)

libraryDependencies ++= Dependencies.core

testOptions += Tests.Argument(TestFrameworks.JUnit, "-v")
