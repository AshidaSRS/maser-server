package com.shin.db.persistence.runtime.queries

import com.shin.db.models.Anime
import doobie.implicits.toSqlInterpolator
import doobie.util.query.Query0
import doobie.util.update.Update0

object AnimeQueries {

  def insertQuery(input: Anime): Update0 =
    sql"""
          INSERT INTO anime (name, year)
          VALUES (${input.name}, ${input.year})
       """.update

  def getQuery(id: Int): Query0[Anime] =
    sql"""SELECT name, year, id, created, updated FROM anime WHERE id = $id"""
      .query[Anime]

  def updateQuery(input: Anime): Update0 =
    sql"""
          UPDATE anime
          SET name = ${input.name}, year = ${input.year}
          WHERE id = ${input.id}
       """.update

  def deleteQuery(id: Int): Update0 =
    sql"""DELETE FROM anime WHERE id = $id""".update

  val listQuery: Query0[Anime] =
    sql"""SELECT name, year, id, created, updated FROM anime ORDER BY id ASC"""
      .query[Anime]
}
