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

import com.shin.Anime
import doobie.implicits.toSqlInterpolator
import doobie.util.query.Query0
import doobie.util.update.Update0

object AnimeQueries {

  def insertQuery(input: Anime): Update0 =
    sql"""
          INSERT INTO "maser"."animes" (name, year)
          VALUES (${input.name}, ${input.year})
       """.update

  def getQuery(id: Long): Query0[Anime] =
    sql"""SELECT name, year, id, created, updated FROM "maser"."animes" WHERE id = $id"""
      .query[Anime]

  def updateQuery(input: Anime): Update0 =
    sql"""
          UPDATE "maser"."animes"
          SET name = ${input.name}, year = ${input.year}
          WHERE id = ${input.id}
       """.update

  def deleteQuery(id: Long): Update0 =
    sql"""DELETE FROM "maser"."animes" WHERE id = $id""".update

  val listQuery: Query0[Anime] =
    sql"""SELECT name, year, id, created, updated FROM "maser"."animes" ORDER BY id ASC"""
      .query[Anime]
}
