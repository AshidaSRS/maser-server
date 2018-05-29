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
  lazy val telegramVersion = "0.5.6"
  lazy val freesVersion = "0.8.0"
  lazy val postgreVersion = "42.1.1"
  lazy val doobieVersion = "0.5.3"
  lazy val http4sVersion = "0.18.11"
  lazy val circeVersion = "0.10.0-M1"
  lazy val blazeVersion = "0.18.11"

  val log = Seq(
    "com.typesafe.scala-logging" %% "scala-logging" % scalaLoggingVersion,
    "org.slf4j" % "slf4j-simple" % slf4jVersion,
    "org.wvlet" %% "wvlet-log" % coolLogVersion
  )

  val test = Seq(
    "org.scalatest" %% "scalatest" % testVersion
  )

  val databaseDependencies = Seq(
    "com.typesafe.slick" %% "slick" % slickVersion,
    "com.typesafe.slick" %% "slick-codegen"  % slickVersion,
    "org.flywaydb" % "flyway-core" % flywayVersion,
    "org.postgresql"  % "postgresql" % postgreVersion,
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
    "org.http4s" %% "http4s-core" % http4sVersion,
    "org.http4s" %% "http4s-circe" % http4sVersion,
    "org.http4s" %% "http4s-dsl" % http4sVersion,
    "org.http4s" %% "http4s-blaze-server" % blazeVersion
  )

  val json = Seq(
    "io.circe" %% "circe-generic" % circeVersion
  )

  val core =
    log ++ test ++ databaseDependencies ++ catsDependencies ++
  scalazDependencies ++ freestyleDependencies ++ http ++ json
}
