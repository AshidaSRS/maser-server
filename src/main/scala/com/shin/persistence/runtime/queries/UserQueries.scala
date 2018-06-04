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

import doobie.implicits.toSqlInterpolator
import doobie.util.query.Query0
import doobie.util.update.Update0
import com.shin.User

object UserQueries {

  def insertQuery(input: User): Update0 =
    sql"""
          INSERT INTO "maser"."users" (alias, telegram_id)
          VALUES (${input.alias}, ${input.telegramId})
       """.update

  def getQuery(id: Long): Query0[User] =
    sql"""SELECT alias, telegram_id, id, created, updated FROM "maser"."users" WHERE id = $id"""
      .query[User]

  def getByTelegramIdQuery(tId: Long): Query0[User] =
    sql"""SELECT alias, telegram_id, id, created, updated FROM "maser"."users" WHERE telegram_id = $tId"""
      .query[User]

  def updateQuery(input: User): Update0 =
    sql"""
          UPDATE "maser"."users"
          SET alias = ${input.alias}, telegram_id = ${input.telegramId}
          WHERE id = ${input.id}
       """.update

  def deleteQuery(id: Long): Update0 =
    sql"""DELETE FROM "maser"."users" WHERE id = $id""".update

  val listQuery: Query0[User] =
    sql"""SELECT alias, telegram_id, id, created, updated FROM "maser"."users" ORDER BY id ASC"""
      .query[User]
}
