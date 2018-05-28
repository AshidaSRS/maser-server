
package com.shin.persistence.runtime

import cats.Monad
import doobie.implicits._
import doobie.util.transactor.Transactor
import com.shin.TelevisionSerie
import com.shin.persistence.TelevisionSerieRepository

class TelevisionSerieRepositoryHandler[F[_]: Monad](implicit T: Transactor[F])
    extends TelevisionSerieRepository.Handler[F] {

  import com.shin.persistence.runtime.queries.TelevisionSerieQueries._

  def insert(input: TelevisionSerie): F[Option[TelevisionSerie]] =
    insertQuery(input)
      .withUniqueGeneratedKeys[Long]("id")
      .flatMap(getQuery(_).option)
      .transact(T)

  def get(id: Long): F[Option[TelevisionSerie]] =
    getQuery(id).option.transact(T)

  def update(tvserie: TelevisionSerie): F[Option[TelevisionSerie]] =
    updateQuery(tvserie).run
      .flatMap(_ => getQuery(tvserie.id.get).option)
      .transact(T)

  def delete(id: Long): F[Int] =
    deleteQuery(id).run.transact(T)

  def list: F[List[TelevisionSerie]] =
    listQuery
      .to[List]
      .transact(T)

}
