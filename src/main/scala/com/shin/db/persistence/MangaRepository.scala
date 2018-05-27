package com.shin.db.persistence

import freestyle.tagless.tagless
import com.shin.db.models.Manga

@tagless(true)
trait MangaRepository[F[_]] {

  def insert(manga: Manga): F[Option[Manga]]

  def get(id: Long): F[Option[Manga]]

  def delete(id: Long): F[Int]

  def update(manga: Manga): F[Option[Manga]]

  def list: F[List[Manga]]

}
