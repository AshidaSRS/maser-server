package com.shin.persistence

import com.shin.TelevisionSerie
import freestyle.tagless.tagless

@tagless(true)
trait TelevisionSerieRepository[F[_]] {

  def insert(tv: TelevisionSerie): F[Option[TelevisionSerie]]

  def get(id: Long): F[Option[TelevisionSerie]]

  def delete(id: Long): F[Int]

  def update(tv: TelevisionSerie): F[Option[TelevisionSerie]]

  def list: F[List[TelevisionSerie]]

}
