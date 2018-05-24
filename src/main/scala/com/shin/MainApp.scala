import wvlet.log.Logger
import com.typesafe.config._
import scala.concurrent.Future
import info.mukel.telegrambot4s.models.Message
import info.mukel.telegrambot4s.api.declarative.{Commands, Callbacks}
import info.mukel.telegrambot4s.api.{TelegramBot, Polling}
import slick.jdbc.MySQLProfile.api.Database
import com.shin.db.migration._
import com.shin.utils.LoggerIntegration
import scala.io.Source

object MainApp extends TelegramBot with Polling with Commands with Callbacks
    with App with LoggerIntegration {

  override lazy val log = Logger("MainApp")

  lazy val token: String = scala.util.Properties
    .envOrNone("BOT_TOKEN")
    .getOrElse(Source.fromFile("bot.token").getLines().mkString)

  implicit val globalConfig: Config = ConfigFactory.load()
  implicit val db: Database = SchemaMigration.doMigration
  TablesGenerator.doGeneration

  log.debug(s"Token: $token")

  def hola(test: String)(msg: Message) = {
    log.debug(s"${msg.from.flatMap(_.username).get} request 'hola'")
    reply(test)(msg)
  }

  onCommand('hola)(hola("hi"))

  log.info("Empesamos :D")

  MainApp.run()
}
