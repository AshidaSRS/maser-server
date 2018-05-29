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

import com.shin.Movie
import doobie.implicits.toSqlInterpolator
import doobie.util.query.Query0
import doobie.util.update.Update0

object MovieQueries {

  def insertQuery(input: Movie): Update0 =
    sql"""
          INSERT INTO "maser"."movies" (name, year)
          VALUES (${input.name}, ${input.year})
       """.update

  def getQuery(id: Long): Query0[Movie] =
    sql"""SELECT name, year, id, created, updated FROM "maser"."movies" WHERE id = $id"""
      .query[Movie]

  def updateQuery(input: Movie): Update0 =
    sql"""
          UPDATE "maser"."movies"
          SET name = ${input.name}, year = ${input.year}
          WHERE id = ${input.id}
       """.update

  def deleteQuery(id: Long): Update0 =
    sql"""DELETE FROM "maser"."movies" WHERE id = $id""".update

  val listQuery: Query0[Movie] =
    sql"""SELECT name, year, id, created, updated FROM "maser"."movies" ORDER BY id ASC"""
      .query[Movie]
}
