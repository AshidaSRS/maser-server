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

package com.shin
package runtime

import java.util.Properties

import cats.effect.IO
import cats.Monad
import com.zaxxer.hikari.{HikariConfig, HikariDataSource}
import doobie._
import doobie.hikari._
import doobie.hikari.implicits._
import doobie.implicits._
import com.shin.persistence._
import com.shin.persistence.runtime._

import scala.concurrent.ExecutionContext

object implicits
    extends ExecutionImplicits
    with RepositoryHandlersImplicits
    with DoobieImplicits

trait RepositoryHandlersImplicits {

  implicit def userRepositoryHandler[F[_]: Monad](
      implicit T: Transactor[F]): UserRepository.Handler[F] =
    new UserRepositoryHandler[F]

  implicit def userContentRepositoryHandler[F[_]: Monad](
      implicit T: Transactor[F]): UserContentRepository.Handler[F] =
    new UserContentRepositoryHandler[F]

  implicit def animeRepositoryHandler[F[_]: Monad](
      implicit T: Transactor[F]): EntertainmentRepository.Handler[F] =
    new EntertainmentRepositoryHandler[F]

  implicit def recommendationRepositoryHandler[F[_]: Monad](
      implicit T: Transactor[F]): RecommendationRepository.Handler[F] =
    new RecommendationRepositoryHandler[F]
}

trait DoobieImplicits {

  implicit val xa: HikariTransactor[IO] =
    HikariTransactor[IO](new HikariDataSource(new HikariConfig(new Properties {
      setProperty("driverClassName", "org.postgresql.Driver")
      setProperty("jdbcUrl", "jdbc:postgresql://localhost:5432/maser")
      setProperty("username", "ashida")
      setProperty("password", "ashida")
      setProperty("maximumPoolSize", "10")
      setProperty("minimumIdle", "10")
      setProperty("idleTimeout", "600000")
      setProperty("connectionTimeout", "30000")
      setProperty("connectionTestQuery", "SELECT 1")
      setProperty("maxLifetime", "1800000")
      setProperty("autoCommit", "true")
    })))
}

trait ExecutionImplicits {

  implicit val ec: ExecutionContext =
    scala.concurrent.ExecutionContext.Implicits.global

}
