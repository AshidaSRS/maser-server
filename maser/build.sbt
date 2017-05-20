import Dependencies._

version := "0.0.1"

scalaVersion := "2.11.8"


resolvers ++= Seq(
  Resolver.sonatypeRepo("snapshots")
)

libraryDependencies ++= Seq(
  "info.mukel" %% "telegrambot4s" % "2.9.0-SNAPSHOT",
  "com.typesafe.scala-logging" %% "scala-logging" % "3.5.0",
  "org.slf4j" % "slf4j-api" % "1.7.5",
  "org.slf4j" % "slf4j-simple" % "1.7.5"
)

lazy val root = (project in file(".")).
  settings(
    inThisBuild(List(
      organization := "com.maser",
      scalaVersion := "2.12.1",
      version      := "0.1.0-SNAPSHOT"
    )),
    name := "Maser",
    libraryDependencies += scalaTest % Test
  )
