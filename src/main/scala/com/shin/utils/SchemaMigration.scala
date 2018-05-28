/*
 * Copyright 2017-2018 47 Degrees, LLC. <http://www.47deg.com>
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.shin.utils

import wvlet.log.Logger
import com.shin.utils.LoggerIntegration
import com.typesafe.config.Config
import org.flywaydb.core.Flyway
import slick.jdbc.PostgresProfile.api._

object SchemaMigration extends LoggerIntegration {

  override lazy val log = Logger("SchemaMigration")

  def doMigration(config: Config): Unit = {
    val url = config.getString("db.properties.url")
    val user = config.getString("db.properties.user")
    val passwd = config.getString("db.properties.password")
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

    //Database.forConfig("db", config)
  }
}
