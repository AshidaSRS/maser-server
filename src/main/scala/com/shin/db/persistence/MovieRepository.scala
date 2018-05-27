package com.shin.db.persistence

import com.shin.db.models.Movie
import freestyle.tagless.tagless

@tagless(true)
trait MovieRepository[F[_]] {

  def insert(movie: Movie): F[Option[Movie]]

  def get(id: Long): F[Option[Movie]]

  def delete(id: Long): F[Int]

  def update(movie: Movie): F[Option[Movie]]

  def list: F[List[Movie]]

}
