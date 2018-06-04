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
import com.shin.persistence.UserRepository
import com.shin.User

class UserRepositoryHandler[F[_]: Monad](implicit T: Transactor[F])
    extends UserRepository.Handler[F] {

  import com.shin.persistence.runtime.queries.UserQueries._

  def insert(input: User): F[Option[User]] =
    insertQuery(input)
      .withUniqueGeneratedKeys[Long]("id")
      .flatMap(getQuery(_).option)
      .transact(T)

  def get(id: Long): F[Option[User]] =
    getQuery(id).option.transact(T)

  def getByTelegramId(tId: Long): F[Option[User]] =
    getByTelegramIdQuery(tId).option.transact(T)

  def update(manga: User): F[Option[User]] =
    updateQuery(manga).run
      .flatMap(_ => getQuery(manga.id.get).option)
      .transact(T)

  def delete(id: Long): F[Int] =
    deleteQuery(id).run.transact(T)

  def list: F[List[User]] =
    listQuery
      .to[List]
      .transact(T)

}
