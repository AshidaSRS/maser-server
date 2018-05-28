package com.shin.persistence.runtime

import cats.Monad
import com.shin.Anime
import doobie.implicits._
import doobie.util.transactor.Transactor
import com.shin.persistence.AnimeRepository

class AnimeRepositoryHandler[F[_]: Monad](implicit T: Transactor[F])
    extends AnimeRepository.Handler[F] {

  import com.shin.persistence.runtime.queries.AnimeQueries._

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
