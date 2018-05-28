package com.shin

import java.sql.Timestamp

sealed abstract class Entity extends Product with Serializable {
  def id: Option[Long]
  def created: Option[Timestamp]
  def updated: Option[Timestamp]
}

final case class Manga(
    name: String,
    year: Int,
    id: Option[Long] = None,
    created: Option[Timestamp] = None,
    updated: Option[Timestamp] = None
) extends Entity

final case class Manhwa(
    name: String,
    year: Int,
    id: Option[Long] = None,
    created: Option[Timestamp] = None,
    updated: Option[Timestamp] = None
) extends Entity

final case class Anime(
    name: String,
    year: Int,
    id: Option[Long] = None,
    created: Option[Timestamp] = None,
    updated: Option[Timestamp] = None
) extends Entity

final case class TelevisionSerie(
    name: String,
    year: Int,
    id: Option[Long] = None,
    created: Option[Timestamp] = None,
    updated: Option[Timestamp] = None
) extends Entity

final case class Movie(
    name: String,
    year: Int,
    id: Option[Long] = None,
    created: Option[Timestamp] = None,
    updated: Option[Timestamp] = None
) extends Entity
