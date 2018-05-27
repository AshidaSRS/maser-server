package com.shin.db.persistence.runtime.queries

import com.shin.db.models.TelevisionSerie
import doobie.implicits.toSqlInterpolator
import doobie.util.query.Query0
import doobie.util.update.Update0

object TelevisionSerieQueries {

  def insertQuery(input: TelevisionSerie): Update0 =
    sql"""
          INSERT INTO television_series (name, year)
          VALUES (${input.name}, ${input.year})
       """.update

  def getQuery(id: Int): Query0[TelevisionSerie] =
    sql"""SELECT name, year, id, created, updated FROM television_series WHERE id = $id"""
      .query[TelevisionSerie]

  def updateQuery(input: TelevisionSerie): Update0 =
    sql"""
          UPDATE television_series
          SET name = ${input.name}, year = ${input.year}
          WHERE id = ${input.id}
       """.update

  def deleteQuery(id: Int): Update0 =
    sql"""DELETE FROM television_series WHERE id = $id""".update

  val listQuery: Query0[TelevisionSerie] =
    sql"""SELECT name, year, id, created, updated FROM television_series ORDER BY id ASC"""
      .query[TelevisionSerie]
}
