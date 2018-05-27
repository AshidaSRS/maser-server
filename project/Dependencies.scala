import sbt._

object Dependencies {

  lazy val dispatchV = "0.11.3"
  lazy val scalaLoggingVersion = "3.7.2"
  lazy val coolLogVersion = "1.2.3"
  lazy val akkaVersion = "2.5.4"
  lazy val mysqlVersion = "8.0.7-dmr"
  lazy val migrationsVersion = "0.3.1"
  lazy val flywayVersion = "4.2.0"
  lazy val bulletinVersion = "0.7.0"
  lazy val scalazVersion = "7.2.15"
  lazy val slickVersion = "3.2.1"
  lazy val catsVersion = "1.0.0-MF"
  lazy val hikariVersion = "3.2.1"
  lazy val testVersion = "3.0.1"
  lazy val slf4jVersion = "1.6.4"
  lazy val telegramVersion = "3.0.15-SNAPSHOT"
  lazy val freesVersion = "0.8.0"
  lazy val postgreVersion = "9.1-901-1.jdbc4"
  lazy val doobieVersion = "0.5.3"

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
    "mysql"                               % "mysql-connector-java"                       % mysqlVersion,
    "com.typesafe.slick"                 %% "slick"                                      % slickVersion,
    "com.typesafe.slick"                 %% "slick-codegen"                              % slickVersion,
    "com.typesafe.slick"                 %% "slick-hikaricp"                             % hikariVersion,
    "org.flywaydb"                        % "flyway-core"                                % flywayVersion,
    "postgresql"                          % "postgresql"                                 % postgreVersion,
    "org.tpolecat" %% "doobie-core"      % doobieVersion,
    "org.tpolecat" %% "doobie-hikari"    % doobieVersion,
    "org.tpolecat" %% "doobie-postgres"  % doobieVersion

  )

  val catsDependencies = Seq(
    "org.typelevel"                      %% "cats-core"                                  % catsVersion
  )

  val scalazDependencies = Seq(
    "org.scalaz"                         %% "scalaz-core"                                % scalazVersion
  )

  val bulletinDependencies = Seq(
    ("com.davegurnell"                   %% "bulletin"                                   % bulletinVersion ).exclude("org.typelevel", "scala-library")
  )

  val freestyleDependencies = Seq (
    "io.frees" %% "frees-core" % freesVersion,
    "io.frees" %% "frees-doobie" % freesVersion
  )

  val core =
    log ++ telegram ++ test ++ databaseDependencies ++ catsDependencies ++
  scalazDependencies ++ bulletinDependencies ++ freestyleDependencies
}
