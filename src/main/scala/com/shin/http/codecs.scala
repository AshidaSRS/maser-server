package com.shin
package http

import cats.Applicative
import cats.effect.Sync
import io.circe.generic.auto._
import org.http4s._
import org.http4s.circe._

object codecs {

  implicit def mangaEncoder[F[_]: Applicative]: EntityEncoder[F, Manga] =
    jsonEncoderOf[F, Manga]
  implicit def mangaDecoder[F[_]: Sync]: EntityDecoder[F, Manga] =
    jsonOf[F, Manga]

}
