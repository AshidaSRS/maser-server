// import freestyle.free._
// import freestyle.free.implicits._
// import cats.implicits._
// import freestyle.free.slick._
// import _root_.slick.jdbc.JdbcBackend
// import _root_.slick.jdbc.MySQLProfile.api._
// import _root_.slick.jdbc.GetResult
// import com.shin.db.migration.SchemaMigration
// import com.typesafe.config.{Config, ConfigFactory}
// import freestyle.free.slick.implicits._
// import java.sql.Timestamp
// import scala.concurrent.Future
// import scala.concurrent.ExecutionContext.Implicits.global
// import com.shin.db.Tables
// import com.shin.db.Tables._

// object MangaActions {

//   implicit val globalConfig: Config = ConfigFactory.load()
//   implicit val getMangaResult: GetResult[MangaRow] = GetResult(r =>
//     MangaRow(r.nextLong, Option(r.nextString), Option(new Timestamp(r.nextDate().getTime)), Option(new Timestamp(r.nextDate().getTime))))

//   val preAction = Tables.Manga.filter(_.id === 1L)
//   val action: DBIO[MangaRow] = preAction.result.map(_.head)
//   println(preAction.result.statements.headOption.get.toString)

//   val slickFrees: FreeS[SlickM.Op, MangaRow] = SlickM[SlickM.Op].run(action)

//   implicit val db = Database.forConfig("db", globalConfig)
//   val f: Future[MangaRow] = slickFrees.interpret[Future]
// }
