package com.shin
package http

import cats.effect.Effect
import cats.implicits._
import com.shin.http._
import org.http4s.implicits._

class Api[F[_]: Effect](implicit mangaApi: MangaApi[F]) {
  val endpoints = mangaApi.endpoints
}

object Api {
  implicit def instance[F[_]: Effect](implicit mangaApi: MangaApi[F]): Api[F] =
    new Api[F]
}