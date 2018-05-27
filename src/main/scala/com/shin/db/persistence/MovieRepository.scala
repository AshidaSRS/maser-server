package com.shin.db.persistence

import com.shin.db.models.Movie
import freestyle.tagless.tagless

@tagless(true)
trait MovieRepository[F[_]] {

  def insert(movie: Movie): F[Option[Movie]]

  def get(id: Int): F[Option[Movie]]

  def delete(id: Int): F[Int]

  def update(movie: Movie): F[Option[Movie]]

  def list: F[List[Movie]]

}
