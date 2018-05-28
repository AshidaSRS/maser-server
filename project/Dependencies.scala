import sbt._

object Dependencies {

  lazy val scalaLoggingVersion = "3.7.2"
  lazy val coolLogVersion = "1.2.3"
  lazy val flywayVersion = "4.2.0"
  lazy val scalazVersion = "7.2.15"
  lazy val slickVersion = "3.2.1"
  lazy val catsVersion = "1.1.0"
  lazy val hikariVersion = "3.2.1"
  lazy val testVersion = "3.0.1"
  lazy val slf4jVersion = "1.6.4"
  lazy val telegramVersion = "3.0.15-SNAPSHOT"
  lazy val freesVersion = "0.8.0"
  lazy val postgreVersion = "9.1-901-1.jdbc4"
  lazy val doobieVersion = "0.5.3"
  lazy val http4sVersion = "0.18.11"
  lazy val circeVersion = "0.9.3"
  lazy val blazeVersion = "0.18.11"

  val log = Seq(
    "com.typesafe.scala-logging" %% "scala-logging" % scalaLoggingVersion
    , "org.slf4j" % "slf4j-simple" % slf4jVersion
    , "org.wvlet" %% "wvlet-log" % coolLogVersion
  )

  val telegram = Seq(
    "info.mukel" %% "telegrambot4s" % telegramVersion
  )

  val test = Seq(
    "org.scalatest" %% "scalatest" % testVersion
  )

  val databaseDependencies = Seq(
    "com.typesafe.slick" %% "slick" % slickVersion,
    "com.typesafe.slick" %% "slick-codegen"  % slickVersion,
    "com.typesafe.slick" %% "slick-hikaricp" % hikariVersion,
    "org.flywaydb" % "flyway-core" % flywayVersion,
    "postgresql"  % "postgresql" % postgreVersion,
    "org.tpolecat" %% "doobie-core" % doobieVersion,
    "org.tpolecat" %% "doobie-hikari"  % doobieVersion,
    "org.tpolecat" %% "doobie-postgres" % doobieVersion

  )

  val catsDependencies = Seq(
    "org.typelevel" %% "cats-core" % catsVersion
  )

  val scalazDependencies = Seq(
    "org.scalaz" %% "scalaz-core" % scalazVersion
  )

  val freestyleDependencies = Seq (
    "io.frees" %% "frees-core" % freesVersion,
    "io.frees" %% "frees-doobie" % freesVersion,
    "io.frees" %% "frees-logging" % freesVersion,
    "io.frees" %% "frees-effects" % freesVersion,
    "io.frees" %% "frees-async"   % freesVersion,
    "io.frees" %% "frees-async-cats-effect" % freesVersion,
    "io.frees" %% "frees-async-guava" % freesVersion,
    "io.frees" %% "frees-cache"   % freesVersion,
    "io.frees" %% "frees-config"  % freesVersion,
    "io.frees" %% "frees-logging" % freesVersion
  )

  val http = Seq(
    "org.http4s" % "http4s-core_2.12" % http4sVersion,
    "org.http4s" %% "http4s-circe" % http4sVersion,
    "org.http4s" %% "http4s-dsl" % http4sVersion,
    "org.http4s" %% "http4s-blaze-server" % blazeVersion
  )

  val json = Seq(
    "io.circe" %% "circe-generic" % circeVersion,
    "io.circe" %% "circe-literal" % circeVersion,
    "io.circe" %% "circe-parser" % circeVersion
  )

  val core =
    log ++ telegram ++ test ++ databaseDependencies ++ catsDependencies ++
  scalazDependencies ++ freestyleDependencies ++ http ++ json
}
