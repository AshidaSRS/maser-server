import sbt._

object Dependencies {

  lazy val dispatchV = "0.11.3"
  lazy val scalaLoggingVersion = "3.7.2"
  lazy val coolLogVersion = "1.2.3"
  lazy val akkaVersion = "2.5.4"


  val log = Seq(
    "com.typesafe.scala-logging" %% "scala-logging" % scalaLoggingVersion
    , "org.slf4j" % "slf4j-simple" % "1.6.4"
    , "org.wvlet" %% "wvlet-log" % coolLogVersion
  )

  val telegram = Seq(
    "info.mukel" %% "telegrambot4s" % "3.0.14"
  )

  val test = Seq(
    "org.scalatest" %% "scalatest" % "3.0.1"
  )

  val core = log ++ telegram ++ test
}
