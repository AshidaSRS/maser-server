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

import com.shin.Manhwa
import doobie.implicits.toSqlInterpolator
import doobie.util.query.Query0
import doobie.util.update.Update0

object ManhwaQueries {

  def insertQuery(input: Manhwa): Update0 =
    sql"""
          INSERT INTO manhwa (name, year)
          VALUES (${input.name}, ${input.year})
       """.update

  def getQuery(id: Long): Query0[Manhwa] =
    sql"""SELECT name, year, id, created, updated FROM manhwa WHERE id = $id"""
      .query[Manhwa]

  def updateQuery(input: Manhwa): Update0 =
    sql"""
          UPDATE manhwa
          SET name = ${input.name}, year = ${input.year}
          WHERE id = ${input.id}
       """.update

  def deleteQuery(id: Long): Update0 =
    sql"""DELETE FROM manhwa WHERE id = $id""".update

  val listQuery: Query0[Manhwa] =
    sql"""SELECT name, year, id, created, updated FROM manhwa ORDER BY id ASC"""
      .query[Manhwa]
}
