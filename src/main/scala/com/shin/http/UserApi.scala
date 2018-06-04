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
package http

import cats.effect.Effect
import cats.implicits._
import com.shin.services.UserService
import io.circe.generic.auto._
import io.circe.syntax._
import org.http4s._
import org.http4s.circe._
import org.http4s.dsl.Http4sDsl

class UserApi[F[_]: Effect](implicit service: UserService[F])
    extends Http4sDsl[F] {

  import codecs._

  private val prefix: String = "users"

  val endpoints: HttpService[F] = HttpService[F] {
    case GET -> Root / `prefix` / IntVar(id) =>
      service.retrieve(id) flatMap { item =>
        item.fold(NotFound(None.asJson))(user => Ok(user.asJson))
      }
    case GET -> Root / `prefix` / "telegram" / LongVar(id) =>
      service.retrieveByTelegramId(id) flatMap { item =>
        item.fold(Ok(None.asJson))(user => Ok(user.asJson))
      }

    case GET -> Root / `prefix` =>
      service.list.flatMap(l => Ok(l.asJson))

    case req @ POST -> Root / `prefix` =>
      for {
        user <- req.as[User]
        insertedUser <- service.insert(user)
        response <- Ok(insertedUser.asJson)
      } yield response

    case req @ PUT -> Root / `prefix` / LongVar(id) =>
      for {
        user <- req.as[User]
        updatedUser <- service.update(user.copy(id = Some(id)))
        reponse <- Ok(updatedUser.asJson)
      } yield reponse

    case DELETE -> Root / `prefix` / LongVar(id) =>
      service.destroy(id) *> Ok()
  }
}

object UserApi {

  implicit def instance[F[_]: Effect](
      implicit service: UserService[F]): UserApi[F] = new UserApi[F]
}
