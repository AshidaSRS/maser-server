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
import com.shin.UserContent
import com.shin.persistence.UserContentRepository

class UserContentRepositoryHandler[F[_]: Monad](implicit T: Transactor[F])
    extends UserContentRepository.Handler[F] {

  import com.shin.persistence.runtime.queries.UserContentQueries._

  def insert(input: UserContent): F[Option[UserContent]] =
    insertQuery(input)
      .withUniqueGeneratedKeys[Long]("id")
      .flatMap(getQuery(_).option)
      .transact(T)

  def get(id: Long): F[Option[UserContent]] =
    getQuery(id).option.transact(T)

  def update(manhwa: UserContent): F[Option[UserContent]] =
    updateQuery(manhwa).run
      .flatMap(_ => getQuery(manhwa.id.get).option)
      .transact(T)

  def delete(id: Long): F[Int] =
    deleteQuery(id).run.transact(T)

  def list: F[List[UserContent]] =
    listQuery
      .to[List]
      .transact(T)

  def listByUserId(userId: Long): F[List[UserContent]] =
    listByUserIdQuery(userId)
      .to[List]
      .transact(T)
}
