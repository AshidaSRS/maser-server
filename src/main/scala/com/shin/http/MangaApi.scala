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

class MangaApi[F[_]: Effect](implicit service: MangaService[F]) extends Http4sDsl[F] {

  import codecs._

  val prefix: String = "mangas"

  val endpoints: HttpService[F] = HttpService[F] {
    case GET -> Root / prefix / IntVar(id) =>
      service.retrieve(id) flatMap { item =>
        item.fold(NotFound(s"Could not find ${service.model} with $id"))(manga => Ok(manga.asJson))
      }

    case GET -> Root / prefix =>
      service.list.flatMap(l => Ok(l.asJson))

    case req @ POST -> Root / prefix =>
      for {
        manga         <- req.as[Manga]
        insertedManga <- service.insert(manga)
        response    <- Ok(insertedManga.asJson)
      } yield response

    case req @ PUT -> Root / prefix / IntVar(id) =>
      for {
        manga        <- req.as[Manga]
        updatedManga <- service.update(manga.copy(id = Some(id)))
        reponse    <- Ok(updatedManga.asJson)
      } yield reponse

    case DELETE -> Root / prefix / IntVar(id) =>
      service.destroy(id) *> Ok()
  }
}

object MangaApi {

  implicit def instance[F[_]: Effect](implicit service: MangaService[F]): MangaApi[F] = new MangaApi[F]
}
