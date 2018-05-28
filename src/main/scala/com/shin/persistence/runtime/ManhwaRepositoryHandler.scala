

package com.shin.persistence.runtime

import cats.Monad
import doobie.implicits._
import doobie.util.transactor.Transactor
import com.shin.Manhwa
import com.shin.persistence.ManhwaRepository

class ManhwaRepositoryHandler[F[_]: Monad](implicit T: Transactor[F])
    extends ManhwaRepository.Handler[F] {

  import com.shin.persistence.runtime.queries.ManhwaQueries._

  def insert(input: Manhwa): F[Option[Manhwa]] =
    insertQuery(input)
      .withUniqueGeneratedKeys[Long]("id")
      .flatMap(getQuery(_).option)
      .transact(T)

  def get(id: Long): F[Option[Manhwa]] =
    getQuery(id).option.transact(T)

  def update(manhwa: Manhwa): F[Option[Manhwa]] =
    updateQuery(manhwa).run
      .flatMap(_ => getQuery(manhwa.id.get).option)
      .transact(T)

  def delete(id: Long): F[Int] =
    deleteQuery(id).run.transact(T)

  def list: F[List[Manhwa]] =
    listQuery
      .to[List]
      .transact(T)

}
