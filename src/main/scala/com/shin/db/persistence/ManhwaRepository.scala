package com.shin.db.persistence

import com.shin.db.models.Manhwa
import freestyle.tagless.tagless

@tagless(true)
trait ManhwaRepository[F[_]] {

  def insert(manhwa: Manhwa): F[Option[Manhwa]]

  def get(id: Long): F[Option[Manhwa]]

  def delete(id: Long): F[Int]

  def update(manhwa: Manhwa): F[Option[Manhwa]]

  def list: F[List[Manhwa]]

}
