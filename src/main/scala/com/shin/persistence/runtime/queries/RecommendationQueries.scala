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

import com.shin.Recommendation
import doobie.implicits.toSqlInterpolator
import doobie.util.query.Query0
import doobie.util.update.Update0

object RecommendationQueries {

  val table = """"maser"."recommendations""""

  def insertQuery(input: Recommendation): Update0 =
    sql"""
          INSERT INTO "maser"."recommendations" (recommender_id, entertainment_id, recommended_id)
          VALUES (${input.recommenderId}, ${input.entertainmentId}, ${input.recommendedId})
       """.update

  def getQuery(id: Long): Query0[Recommendation] =
    sql"""SELECT recommender_id, entertainment_id, recommended_id, id, created, updated FROM "maser"."recommendations" WHERE id = $id"""
      .query[Recommendation]

  def updateQuery(input: Recommendation): Update0 =
    sql"""
          UPDATE "maser"."recommendations"
          SET recommender_id = ${input.recommenderId}, recommended_id = ${input.recommendedId}, entertainment_id = ${input.entertainmentId}
          WHERE id = ${input.id}
       """.update

  def deleteQuery(id: Long): Update0 =
    sql"""DELETE FROM "maser"."recommendations" WHERE id = $id""".update

  val listQuery: Query0[Recommendation] =
    sql"""SELECT recommender_id, entertainment_id, recommended_id, id, created, updated FROM "maser"."recommendations" ORDER BY id ASC"""
      .query[Recommendation]

  def listByRecommededIdQuery(recommendedId: Long): Query0[Recommendation] =
    sql"""SELECT recommender_id, entertainment_id, recommended_id, id, created, updated FROM "maser"."recommendations" WHERE recommended_id = $recommendedId"""
      .query[Recommendation]

  def listByRecommederIdQuery(recommenderId: Long): Query0[Recommendation] =
    sql"""SELECT recommender_id, entertainment_id, recommended_id, id, created, updated FROM "maser"."recommendations" WHERE recommender_id = $recommenderId"""
      .query[Recommendation]

  def listByREntertainmentIdQuery(
      entertainmentId: Long): Query0[Recommendation] =
    sql"""SELECT recommender_id, entertainment_id, recommended_id, id, created, updated FROM "maser"."recommendations" WHERE entertainment_id = $entertainmentId"""
      .query[Recommendation]

  def listByIdsQuery(recommenderId: Long,
                     recommendedId: Long): Query0[Recommendation] =
    sql"""SELECT recommender_id, entertainment_id, recommended_id, id, created, updated FROM "maser"."recommendations" WHERE recommender_id = $recommenderId AND recommended_id = $recommendedId"""
      .query[Recommendation]

}
