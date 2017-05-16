package maser

import info.mukel.telegrambot4s.Implicits._
import info.mukel.telegrambot4s.models._
import info.mukel.telegrambot4s.api._
import com.typesafe.scalalogging.Logger

trait Methods extends TelegramBot with Polling with Commands {
  val a = InlineKeyboardButton("a","b")
  val board = InlineKeyboardMarkup(List(List(a)))
  def simple_command(c: String, desc: String, response: String) {
    val logger: Logger = Logger("Simple")
    val order = s"/$c"
    on(order, desc) {
      implicit msg => args => {
        logger.debug(msg.chat.id.toString)
        logger.info(s"$order answering: $response")
        reply(response, None,None,None,None,board)
      }
    }
  }
}

case class MaserCommands(token: String) extends Methods{
  simple_command("test", "test", "test")
  simple_command("test2", "test2", "test2")
}
