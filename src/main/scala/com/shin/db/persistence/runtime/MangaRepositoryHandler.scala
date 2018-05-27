package com.shin.db.persistence.runtime

import cats.Monad
import doobie.implicits._
import doobie.util.transactor.Transactor
import com.shin.db.persistence.MangaRepository
import com.shin.db.models.Manga

class MangaRepositoryHandler[F[_]: Monad](implicit T: Transactor[F])
    extends MangaRepository.Handler[F] {

  import com.shin.db.persistence.runtime.queries.MangaQueries._

  def insert(input: Manga): F[Option[Manga]] =
    insertQuery(input)
      .withUniqueGeneratedKeys[Long]("id")
      .flatMap(getQuery(_).option)
      .transact(T)

  def get(id: Long): F[Option[Manga]] =
    getQuery(id).option.transact(T)

  def update(manga: Manga): F[Option[Manga]] =
    updateQuery(manga).run
      .flatMap(_ => getQuery(manga.id.get).option)
      .transact(T)

  def delete(id: Long): F[Int] =
    deleteQuery(id).run.transact(T)

  def list: F[List[Manga]] =
    listQuery
      .to[List]
      .transact(T)

}
