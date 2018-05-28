package com.shin
package runtime

import java.util.Properties

import cats.effect.IO
import cats.Monad
import com.zaxxer.hikari.{HikariConfig, HikariDataSource}
import doobie._
import doobie.hikari._
import doobie.hikari.implicits._
import doobie.implicits._
import com.shin.persistence._
import com.shin.persistence.runtime._

import scala.concurrent.ExecutionContext

object implicits extends ExecutionImplicits with RepositoryHandlersImplicits with DoobieImplicits

trait RepositoryHandlersImplicits {

  implicit def mangaRepositoryHandler[F[_]: Monad](
      implicit T: Transactor[F]): MangaRepository.Handler[F] =
    new MangaRepositoryHandler[F]

}

trait DoobieImplicits {

  implicit val xa: HikariTransactor[IO] =
    HikariTransactor[IO](new HikariDataSource(new HikariConfig(new Properties {
      setProperty("driverClassName", "org.postgresql.Driver")
      setProperty("jdbcUrl", "jdbc:postgresql://localhost:5432/maser")
      setProperty("username", "ashida")
      setProperty("password", "ashida")
      setProperty("maximumPoolSize", "10")
      setProperty("minimumIdle", "10")
      setProperty("idleTimeout", "600000")
      setProperty("connectionTimeout", "30000")
      setProperty("connectionTestQuery", "SELECT 1")
      setProperty("maxLifetime", "1800000")
      setProperty("autoCommit", "true")
    })))
}

trait ExecutionImplicits {

  implicit val ec: ExecutionContext =
    scala.concurrent.ExecutionContext.Implicits.global

}
