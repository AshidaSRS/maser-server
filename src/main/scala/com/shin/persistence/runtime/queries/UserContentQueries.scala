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

import com.shin.UserContent
import doobie.implicits.toSqlInterpolator
import doobie.util.query.Query0
import doobie.util.update.Update0

object UserContentQueries {

  def insertQuery(input: UserContent): Update0 =
    sql"""
          INSERT INTO "maser"."user_contents" (user_id, entertainment_id)
          VALUES (${input.userId}, ${input.entertainmentId})
       """.update

  def getQuery(id: Long): Query0[UserContent] =
    sql"""SELECT user_id, entertainment_id, id, created FROM "maser"."user_contents" WHERE id = $id"""
      .query[UserContent]

  def updateQuery(input: UserContent): Update0 =
    sql"""
          UPDATE "maser"."user_contents"
          SET user_id = ${input.userId}, entertainment_id = ${input.entertainmentId}
          WHERE id = ${input.id}
       """.update

  def deleteQuery(id: Long): Update0 =
    sql"""DELETE FROM "maser"."user_contents" WHERE id = $id""".update

  val listQuery: Query0[UserContent] =
    sql"""SELECT user_id, entertainment_id, id, created FROM "maser"."user_contents" ORDER BY id ASC"""
      .query[UserContent]

  def listByUserIdQuery(userId: Long): Query0[UserContent] =
    sql"""SELECT user_id, entertainment_id, id, created FROM "maser"."user_contents" WHERE user_id = $userId"""
      .query[UserContent]
}
