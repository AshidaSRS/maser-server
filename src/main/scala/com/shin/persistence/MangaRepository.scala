package com.shin.persistence

import com.shin.Manga
import freestyle.tagless.tagless

@tagless(true)
trait MangaRepository[F[_]] {

  def insert(manga: Manga): F[Option[Manga]]

  def get(id: Long): F[Option[Manga]]

  def delete(id: Long): F[Int]

  def update(manga: Manga): F[Option[Manga]]

  def list: F[List[Manga]]

}
