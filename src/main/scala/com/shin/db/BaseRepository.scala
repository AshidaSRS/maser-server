
import freestyle.free._
import freestyle.free.implicits._
import cats.implicits._
import freestyle.free.slick._
import _root_.slick.jdbc.JdbcBackend
import _root_.slick.jdbc.MySQLProfile.api._
import _root_.slick.jdbc.GetResult
import com.shin.db.migration.SchemaMigration
import com.typesafe.config.{Config, ConfigFactory}
import freestyle.free.slick.implicits._

import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global

case class Manga(id: Long, name: String)

object Manga {

  implicit val globalConfig: Config = ConfigFactory.load()
  implicit val getMangaResult: GetResult[Manga] = GetResult(r => Manga(r.nextLong, r.nextString))

  val getManga: DBIO[Manga] = sql"SELECT 1, 'Boruto'".as[Manga].head

  val slickFrees: FreeS[SlickM.Op, Manga] = SlickM[SlickM.Op].run(getManga)

  implicit val db = Database.forConfig("db", globalConfig)
  val f: Future[Manga] = slickFrees.interpret[Future]
}
