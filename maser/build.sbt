import Dependencies._

version := "0.0.1"

scalaVersion := "2.11.8"
lazy val dispatchV = "0.11.3"

resolvers ++= Seq(
  Resolver.sonatypeRepo("snapshots")
)


libraryDependencies ++= Seq(
  "info.mukel" %% "telegrambot4s" % "2.9.0-SNAPSHOT",
  "com.typesafe.scala-logging" %% "scala-logging" % "3.5.0",
  "org.slf4j" % "slf4j-api" % "1.7.5",
  "org.slf4j" % "slf4j-simple" % "1.7.5",
  "com.typesafe.akka" %% "akka-testkit" % "2.4.1" % "test" withSources(),
  "com.typesafe.akka" %% "akka-actor" % "2.4.6",
  "com.typesafe.akka" %% "akka-http" % "10.0.4" withSources() withJavadoc(),
  "org.scala-lang.modules" %% "scala-xml" % "1.0.2",
  "org.scala-lang.modules" %% "scala-parser-combinators" % "1.0.1",
  "net.databinder.dispatch" %% "dispatch-core" % dispatchV,
  "com.github.marklister" %% "base64" % "0.2.3"
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
