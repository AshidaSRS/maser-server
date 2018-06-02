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

package com.shin.persistence.runtime.queries

import com.shin.Entertainment
import doobie.implicits.toSqlInterpolator
import doobie.util.query.Query0
import doobie.util.update.Update0

object EntertainmentQueries {

  val table = """"maser"."entertainments""""

  def insertQuery(input: Entertainment): Update0 =
    sql"""
          INSERT INTO "maser"."entertainments" (name, rate, model)
          VALUES (${input.name}, ${input.rate}, ${input.model})
       """.update

  def getQuery(id: Long): Query0[Entertainment] =
    sql"""SELECT name, rate, model, id, created, updated FROM "maser"."entertainments" WHERE id = $id"""
      .query[Entertainment]

  def updateQuery(input: Entertainment): Update0 =
    sql"""
          UPDATE "maser"."entertainments"
          SET name = ${input.name}, rate = ${input.rate}
          WHERE id = ${input.id}
       """.update

  def deleteQuery(id: Long): Update0 =
    sql"""DELETE FROM "maser"."entertainments" WHERE id = $id""".update

  val listQuery: Query0[Entertainment] =
    sql"""SELECT name, rate, model, id, created, updated FROM "maser"."entertainments" ORDER BY id ASC"""
      .query[Entertainment]

  def getLikeNameQuery(name: String): Query0[Entertainment] =
    sql"""SELECT name, rate, model, id, created, updated FROM "maser"."entertainments" WHERE LOWER(name) ~ ${name.toLowerCase}"""
      .query[Entertainment]

}
