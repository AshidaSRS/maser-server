import com.shin.db.migration._
import com.shin.utils.LoggerIntegration
import com.typesafe.config._
import info.mukel.telegrambot4s.api.declarative.{Callbacks, Commands, ToCommand}
import info.mukel.telegrambot4s.api.{Polling, TelegramBot}
import info.mukel.telegrambot4s.models.Message
import slick.jdbc.MySQLProfile.api._
import wvlet.log.Logger

import scala.concurrent.Future
import scala.io.Source

object MainApp extends TelegramBot with Polling with Commands with Callbacks
    with App with LoggerIntegration {

  override lazy val log = Logger("MainApp")

  lazy val token: String = scala.util.Properties
    .envOrNone("BOT_TOKEN")
    .getOrElse(Source.fromFile("bot.token").getLines().mkString)

  implicit val globalConfig: Config = ConfigFactory.load()
  implicit val db: Database = SchemaMigration.doMigration
  //TablesGenerator.doGeneration

  log.debug(s"Token: $token")

   def hola(test: String)(msg: Message) = reply(test)(msg)

   def createCommand[T : ToCommand](command: T, f: Message => Future[Message]) = {
     onCommand(command){implicit msg =>
       log.debug(s"${msg.from.flatMap(_.username).get} request $command")
       f(msg)
     }
   }

  createCommand('meh, hola("Meh"))

  MangaActions.f.map(x => log.info(x))
  log.info("Maser start")

  MainApp.run()
}
