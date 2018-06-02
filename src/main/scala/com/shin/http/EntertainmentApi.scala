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
import com.shin.services.EntertainmentService
import io.circe.generic.auto._
import io.circe.syntax._
import org.http4s._
import org.http4s.circe._
import org.http4s.dsl.Http4sDsl

class EntertainmentApi[F[_]: Effect](implicit service: EntertainmentService[F])
    extends Http4sDsl[F] {

  import codecs._

  object NameQueryParamMatcher extends QueryParamDecoderMatcher[String]("name")

  private val prefix: String = "entertainments"

  val endpoints: HttpService[F] = HttpService[F] {
    case GET -> Root / `prefix` / IntVar(id) =>
      service.retrieve(id) flatMap { item =>
        item.fold(NotFound(s"Could not find ${service.model} with $id"))(
          entertainment => Ok(entertainment.asJson))
      }

    case GET -> Root / `prefix` :? NameQueryParamMatcher(name) =>
      service.retrieveLikeName(name).flatMap(l => Ok(l.asJson))

    case GET -> Root / `prefix` =>
      service.list.flatMap(l => Ok(l.asJson))

    case req @ POST -> Root / `prefix` =>
      for {
        entertainment <- req.as[Entertainment]
        insertedEntertainment <- service.insert(entertainment)
        response <- Ok(insertedEntertainment.asJson)
      } yield response

    case req @ PUT -> Root / `prefix` / IntVar(id) =>
      for {
        entertainment <- req.as[Entertainment]
        updatedEntertainment <- service.update(
          entertainment.copy(id = Some(id)))
        reponse <- Ok(updatedEntertainment.asJson)
      } yield reponse

    case DELETE -> Root / `prefix` / IntVar(id) =>
      service.destroy(id) *> Ok()

  }
}

object EntertainmentApi {

  implicit def instance[F[_]: Effect](
      implicit service: EntertainmentService[F]): EntertainmentApi[F] =
    new EntertainmentApi[F]
}
