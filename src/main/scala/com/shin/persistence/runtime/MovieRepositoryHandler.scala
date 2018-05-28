
package com.shin.persistence.runtime

import cats.Monad
import doobie.implicits._
import doobie.util.transactor.Transactor
import com.shin.Movie
import com.shin.persistence.MovieRepository

class MovieRepositoryHandler[F[_]: Monad](implicit T: Transactor[F])
    extends MovieRepository.Handler[F] {

  import com.shin.persistence.runtime.queries.MovieQueries._

  def insert(input: Movie): F[Option[Movie]] =
    insertQuery(input)
      .withUniqueGeneratedKeys[Long]("id")
      .flatMap(getQuery(_).option)
      .transact(T)

  def get(id: Long): F[Option[Movie]] =
    getQuery(id).option.transact(T)

  def update(movie: Movie): F[Option[Movie]] =
    updateQuery(movie).run
      .flatMap(_ => getQuery(movie.id.get).option)
      .transact(T)

  def delete(id: Long): F[Int] =
    deleteQuery(id).run.transact(T)

  def list: F[List[Movie]] =
    listQuery
      .to[List]
      .transact(T)

}
