package com.shin.db.persistence.runtime.queries

import com.shin.db.models.Movie
import doobie.implicits.toSqlInterpolator
import doobie.util.query.Query0
import doobie.util.update.Update0

object MovieQueries {

  def insertQuery(input: Movie): Update0 =
    sql"""
          INSERT INTO movie (name, year)
          VALUES (${input.name}, ${input.year})
       """.update

  def getQuery(id: Int): Query0[Movie] =
    sql"""SELECT name, year, id, created, updated FROM movie WHERE id = $id"""
      .query[Movie]

  def updateQuery(input: Movie): Update0 =
    sql"""
          UPDATE movie
          SET name = ${input.name}, year = ${input.year}
          WHERE id = ${input.id}
       """.update

  def deleteQuery(id: Int): Update0 =
    sql"""DELETE FROM movie WHERE id = $id""".update

  val listQuery: Query0[Movie] =
    sql"""SELECT name, year, id, created, updated FROM movie ORDER BY id ASC"""
      .query[Movie]
}
