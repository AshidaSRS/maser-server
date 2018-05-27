package com.shin.db.persistence.runtime

import cats.Monad
import doobie.implicits._
import doobie.util.transactor.Transactor
import com.shin.db.persistence.AnimeRepository
import com.shin.db.models.Anime

class AnimeRepositoryHandler[F[_]: Monad](implicit T: Transactor[F])
    extends AnimeRepository.Handler[F] {

  import com.shin.db.persistence.runtime.queries.AnimeQueries._

  def insert(input: Anime): F[Option[Anime]] =
    insertQuery(input)
      .withUniqueGeneratedKeys[Long]("id")
      .flatMap(getQuery(_).option)
      .transact(T)

  def get(id: Long): F[Option[Anime]] =
    getQuery(id).option.transact(T)

  def update(anime: Anime): F[Option[Anime]] =
    updateQuery(anime).run
      .flatMap(_ => getQuery(anime.id.get).option)
      .transact(T)

  def delete(id: Long): F[Int] =
    deleteQuery(id).run.transact(T)

  def list: F[List[Anime]] =
    listQuery
      .to[List]
      .transact(T)

}
