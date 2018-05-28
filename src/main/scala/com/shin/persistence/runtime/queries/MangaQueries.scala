

package com.shin.persistence.runtime.queries

import doobie.implicits.toSqlInterpolator
import doobie.util.query.Query0
import doobie.util.update.Update0
import com.shin.Manga

object MangaQueries {

  def insertQuery(input: Manga): Update0 =
    sql"""
          INSERT INTO manga (name, year)
          VALUES (${input.name}, ${input.year})
       """.update

  def getQuery(id: Long): Query0[Manga] =
    sql"""SELECT name, year, id, created, updated FROM manga WHERE id = $id"""
      .query[Manga]

  def updateQuery(input: Manga): Update0 =
    sql"""
          UPDATE manga
          SET name = ${input.name}, year = ${input.year}
          WHERE id = ${input.id}
       """.update

  def deleteQuery(id: Long): Update0 =
    sql"""DELETE FROM manga WHERE id = $id""".update

  val listQuery: Query0[Manga] =
    sql"""SELECT name, year, id, created, updated FROM manga ORDER BY rid ASC"""
      .query[Manga]
}
