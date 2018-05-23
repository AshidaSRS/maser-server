import wvlet.log.Logger
import scala.concurrent.Future
import info.mukel.telegrambot4s.models.Message
import info.mukel.telegrambot4s.api.declarative.{Commands, Callbacks}
import info.mukel.telegrambot4s.api.{TelegramBot, Polling}

import scala.io.Source
import wvlet.log.LogFormatter.SourceCodeLogFormatter
import wvlet.log.LogLevel._

object MainApp extends App with TelegramBot with Polling with Commands with Callbacks{

  val log = Logger("Maser-Main")
  log.setLogLevel(DEBUG)
  log.setFormatter(SourceCodeLogFormatter)

  lazy val token: String = scala.util.Properties
    .envOrNone("BOT_TOKEN")
    .getOrElse(Source.fromFile("bot.token").getLines().mkString)

  def hola(test: String)(msg: Message) = {
    log.debug(s"$msg -> $test")
    reply(test)(msg)
  }

  onCommand('hola)(hola("hi"))

  log.info("Empesamos :D")

}
