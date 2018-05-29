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
import com.shin.services.TelevisionSerieService
import io.circe.generic.auto._
import io.circe.syntax._
import org.http4s._
import org.http4s.circe._
import org.http4s.dsl.Http4sDsl

class TelevisionSerieApi[F[_]: Effect](
    implicit service: TelevisionSerieService[F])
    extends Http4sDsl[F] {

  import codecs._

  private val prefix: String = "television-series"

  val endpoints: HttpService[F] = HttpService[F] {
    case GET -> Root / `prefix` / IntVar(id) =>
      service.retrieve(id) flatMap { item =>
        item.fold(NotFound(s"Could not find ${service.model} with $id"))(
          televisionSerie => Ok(televisionSerie.asJson))
      }

    case GET -> Root / `prefix` =>
      service.list.flatMap(l => Ok(l.asJson))

    case req @ POST -> Root / `prefix` =>
      for {
        televisionSerie <- req.as[TelevisionSerie]
        insertedTelevisionSerie <- service.insert(televisionSerie)
        response <- Ok(insertedTelevisionSerie.asJson)
      } yield response

    case req @ PUT -> Root / `prefix` / IntVar(id) =>
      for {
        televisionSerie <- req.as[TelevisionSerie]
        updatedTelevisionSerie <- service.update(
          televisionSerie.copy(id = Some(id)))
        reponse <- Ok(updatedTelevisionSerie.asJson)
      } yield reponse

    case DELETE -> Root / `prefix` / IntVar(id) =>
      service.destroy(id) *> Ok()
  }
}

object TelevisionSerieApi {

  implicit def instance[F[_]: Effect](
      implicit service: TelevisionSerieService[F]): TelevisionSerieApi[F] =
    new TelevisionSerieApi[F]
}
