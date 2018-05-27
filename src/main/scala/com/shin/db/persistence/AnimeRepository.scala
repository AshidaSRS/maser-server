package com.shin.db.persistence

import com.shin.db.models.Anime
import freestyle.tagless.tagless

@tagless(true)
trait AnimeRepository[F[_]] {

  def insert(anime: Anime): F[Option[Anime]]

  def get(id: Int): F[Option[Anime]]

  def delete(id: Int): F[Int]

  def update(anime: Anime): F[Option[Anime]]

  def list: F[List[Anime]]

}
