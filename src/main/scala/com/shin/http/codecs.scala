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

import java.sql.Timestamp
import io.circe._
import cats.Applicative
import cats.effect.Sync
import io.circe.generic.auto._
import org.http4s._
import org.http4s.circe._
import io.circe.syntax._

object codecs {
  implicit val timestampEncoder: Encoder[Timestamp] =
    Encoder.instance(a => a.getTime().asJson)
  implicit val timestampDecoder: Decoder[Timestamp] =
    Decoder.instance(a => a.as[Long].map(new Timestamp(_)))

  implicit def mangaEncoder[F[_]: Applicative]: EntityEncoder[F, Manga] =
    jsonEncoderOf[F, Manga]
  implicit def mangaDecoder[F[_]: Sync]: EntityDecoder[F, Manga] =
    jsonOf[F, Manga]

  implicit def manhwaEncoder[F[_]: Applicative]: EntityEncoder[F, Manhwa] =
    jsonEncoderOf[F, Manhwa]
  implicit def manhwaDecoder[F[_]: Sync]: EntityDecoder[F, Manhwa] =
    jsonOf[F, Manhwa]

  implicit def movieEncoder[F[_]: Applicative]: EntityEncoder[F, Movie] =
    jsonEncoderOf[F, Movie]
  implicit def movieDecoder[F[_]: Sync]: EntityDecoder[F, Movie] =
    jsonOf[F, Movie]

  implicit def televisionSerieEncoder[F[_]: Applicative]
    : EntityEncoder[F, TelevisionSerie] =
    jsonEncoderOf[F, TelevisionSerie]
  implicit def televisionSerieDecoder[F[_]: Sync]
    : EntityDecoder[F, TelevisionSerie] =
    jsonOf[F, TelevisionSerie]

  implicit def animeEncoder[F[_]: Applicative]: EntityEncoder[F, Anime] =
    jsonEncoderOf[F, Anime]
  implicit def animeDecoder[F[_]: Sync]: EntityDecoder[F, Anime] =
    jsonOf[F, Anime]

}
