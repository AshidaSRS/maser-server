package com.shin.persistence.runtime.queries

import com.shin.Manhwa
import doobie.implicits.toSqlInterpolator
import doobie.util.query.Query0
import doobie.util.update.Update0

object ManhwaQueries {

  def insertQuery(input: Manhwa): Update0 =
    sql"""
          INSERT INTO manhwa (name, year)
          VALUES (${input.name}, ${input.year})
       """.update

  def getQuery(id: Long): Query0[Manhwa] =
    sql"""SELECT name, year, id, created, updated FROM manhwa WHERE id = $id"""
      .query[Manhwa]

  def updateQuery(input: Manhwa): Update0 =
    sql"""
          UPDATE manhwa
          SET name = ${input.name}, year = ${input.year}
          WHERE id = ${input.id}
       """.update

  def deleteQuery(id: Long): Update0 =
    sql"""DELETE FROM manhwa WHERE id = $id""".update

  val listQuery: Query0[Manhwa] =
    sql"""SELECT name, year, id, created, updated FROM manhwa ORDER BY id ASC"""
      .query[Manhwa]
}
