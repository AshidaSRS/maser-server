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

package com.shin.persistence.runtime

import cats.Monad
import doobie.implicits._
import doobie.util.transactor.Transactor
import com.shin.Recommendation
import com.shin.persistence.RecommendationRepository

class RecommendationRepositoryHandler[F[_]: Monad](implicit T: Transactor[F])
    extends RecommendationRepository.Handler[F] {

  import com.shin.persistence.runtime.queries.RecommendationQueries._

  def insert(input: Recommendation): F[Option[Recommendation]] =
    insertQuery(input)
      .withUniqueGeneratedKeys[Long]("id")
      .flatMap(getQuery(_).option)
      .transact(T)

  def get(id: Long): F[Option[Recommendation]] =
    getQuery(id).option.transact(T)

  def update(recommendation: Recommendation): F[Option[Recommendation]] =
    updateQuery(recommendation).run
      .flatMap(_ => getQuery(recommendation.id.get).option)
      .transact(T)

  def delete(id: Long): F[Int] =
    deleteQuery(id).run.transact(T)

  def list: F[List[Recommendation]] =
    listQuery
      .to[List]
      .transact(T)

  def listByRecommededId(recommendedId: Long): F[List[Recommendation]] =
    listByRecommededIdQuery(recommendedId)
      .to[List]
      .transact(T)

  def listByRecommederId(recommenderId: Long): F[List[Recommendation]] =
    listByRecommederIdQuery(recommenderId)
      .to[List]
      .transact(T)

  def listByEntertainmentId(entertainmentId: Long): F[List[Recommendation]] =
    listByREntertainmentIdQuery(entertainmentId)
      .to[List]
      .transact(T)

  def listByIds(recommenderId: Long,
                recommendedId: Long): F[List[Recommendation]] =
    listByIdsQuery(recommenderId, recommendedId)
      .to[List]
      .transact(T)
}
