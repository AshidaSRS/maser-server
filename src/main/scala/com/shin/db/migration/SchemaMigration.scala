package com.shin.db.migration

import com.typesafe.config.Config
import wvlet.log.Logger
import com.shin.utils.LoggerIntegration
import com.typesafe.config.Config
import org.flywaydb.core.Flyway
import slick.jdbc.MySQLProfile.api._


object SchemaMigration extends LoggerIntegration {

  override lazy val log = Logger("SchemaMigration")

  def doMigration(implicit config: Config): Database = {
    val url = config.getString("db.url")
    val user = config.getString("db.user")
    val passwd = config.getString("db.password")
    val schemaName = config.getString("db_name")
    val migrationsPath = "filesystem:src/main/resources/db/migration"


    log.info(s"Doing migration for $schemaName")
    val flyway = new Flyway()
    flyway.setDataSource(url, user, passwd)
    flyway.setSchemas(schemaName)
    flyway.setLocations(migrationsPath)
    flyway.setInstalledBy("Ashida")
    flyway.migrate()
    flyway.validate()

    Database.forConfig("db", config)
  }
}
