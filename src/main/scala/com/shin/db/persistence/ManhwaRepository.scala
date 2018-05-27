package com.shin.db.persistence

import com.shin.db.models.Manhwa
import freestyle.tagless.tagless

@tagless(true)
trait ManhwaRepository[F[_]] {

  def insert(manhwa: Manhwa): F[Option[Manhwa]]

  def get(id: Int): F[Option[Manhwa]]

  def delete(id: Int): F[Int]

  def update(manhwa: Manhwa): F[Option[Manhwa]]

  def list: F[List[Manhwa]]

}
