package com.shin.db.migration

import com.typesafe.config.{Config, ConfigFactory}
import slick.codegen.SourceCodeGenerator
import slick.jdbc.JdbcProfile
import com.shin.utils.LoggerIntegration
import scala.concurrent.duration.Duration
import scala.concurrent.{Await, ExecutionContext}
import wvlet.log.Logger


object TablesGenerator extends LoggerIntegration {

  override lazy val log = Logger("TablesGenerator")

  def run(slickDriver: String, jdbcDriver: String, url: String, outputDir: String,
    pkg: String, user: Option[String], password: Option[String]): Unit = {

    val driver: JdbcProfile =
      Class.forName(slickDriver + "$").getField("MODULE$").get(null).asInstanceOf[JdbcProfile]
    val dbFactory = driver.api.Database
    val db = dbFactory.forURL(url, driver = jdbcDriver,
      user = user.getOrElse(null), password = password.getOrElse(null), keepAliveConnection = true)
    try {
      val m = Await.result(db.run(driver.createModel(None, false)(ExecutionContext.global).withPinnedSession), Duration.Inf)
      new SourceCodeGenerator(m) {
        override def Table = new Table(_) {
          override def autoIncLastAsOption = true
        }
      }.writeToFile(slickDriver,outputDir,pkg)
    } finally db.close
  }

  def doGeneration(implicit config: Config): Unit = {
    log.info(s"Generating case classes for ${config.getString("db_name")} database")
    run(
      slickDriver = config.getString("db.slick.driver"),
      jdbcDriver = config.getString("db.driver"),
      url = config.getString("db.url"),
      user = Option(config.getString("db.user")),
      password = Option(config.getString("db.password")),
      outputDir = "src/main/scala",
      pkg = "com.shin.db"
    )
  }

}
