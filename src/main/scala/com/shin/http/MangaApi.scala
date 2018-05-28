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
import com.shin.services.MangaService
import io.circe.generic.auto._
import io.circe.syntax._
import org.http4s._
import org.http4s.circe._
import org.http4s.dsl.Http4sDsl

class MangaApi[F[_]: Effect](implicit service: MangaService[F])
    extends Http4sDsl[F] {

  import codecs._

  val prefix: String = "mangas"

  val endpoints: HttpService[F] = HttpService[F] {
    case GET -> Root / prefix / IntVar(id) =>
      service.retrieve(id) flatMap { item =>
        item.fold(NotFound(s"Could not find ${service.model} with $id"))(
          manga => Ok(manga.asJson))
      }

    case GET -> Root / prefix =>
      service.list.flatMap(l => Ok(l.asJson))

    case req @ POST -> Root / prefix =>
      for {
        manga <- req.as[Manga]
        insertedManga <- service.insert(manga)
        response <- Ok(insertedManga.asJson)
      } yield response

    case req @ PUT -> Root / prefix / IntVar(id) =>
      for {
        manga <- req.as[Manga]
        updatedManga <- service.update(manga.copy(id = Some(id)))
        reponse <- Ok(updatedManga.asJson)
      } yield reponse

    case DELETE -> Root / prefix / IntVar(id) =>
      service.destroy(id) *> Ok()
  }
}

object MangaApi {

  implicit def instance[F[_]: Effect](
      implicit service: MangaService[F]): MangaApi[F] = new MangaApi[F]
}