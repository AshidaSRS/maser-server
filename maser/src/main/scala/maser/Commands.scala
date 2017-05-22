package maser

import info.mukel.telegrambot4s.Implicits._
import info.mukel.telegrambot4s.models._
import info.mukel.telegrambot4s.api._
import com.typesafe.scalalogging.Logger
import models._


trait Methods extends TelegramBot with Polling with Commands {

  type InlineKeyFile = List[InlineKeyboardButton]

  def command(c: String, desc: String, response: String, pagePreview: Option[Boolean] = None,
    notification: Option[Boolean] = None, replyToId: Option[Long] = None, board: Option[InlineKeyboardMarkup] = None) {
    val logger: Logger = Logger("Command")
    val order = s"/$c"
    on(order, desc) {
      implicit msg => args => {
        logger.debug(msg.chat.id.toString)
        logger.info(s"$order answering: $response")
        reply(response, None, pagePreview, notification, replyToId, board)
      }
    }
  }

  def genInlineButton[T](t: String, tag: T): InlineKeyboardButton = {
    import maser.CommandTypes.ButtonImplicits._
    val button = InlineKeyboardButton(text = t)
    tag match {
      case t:URL => button.toURLButton(t)
      case t:CallBack => button.toCallBakcButton(t)
      case t:Query => button.toQueryButton(t)
      case t:QueryChat => button.toQueryChatButton(t)
      case _ => button
  }
}

  def genInlineKeyboard(l: List[InlineKeyFile]): InlineKeyboardMarkup = {
    InlineKeyboardMarkup(inlineKeyboard = l)
  }
}

case class MaserCommands(token: String) extends Methods{
  command(c = "atest", desc = "test", response = "test")
  command(c = "btest", desc = "test2", response = "test2")
  val b: InlineKeyboardMarkup = genInlineKeyboard(
    List(
      List(
        genInlineButton[URL]("Test Url", URL("https://acmupm.es")),
        genInlineButton[URL]("Test Url", URL("https://notengoenie.com"))
      ),
      List(
        genInlineButton[QueryChat]("Test QueryChat", QueryChat("test"))
      )
    )
  )
  command(c = "ctest", desc = "testin", response = "Test Inline Buttons", board = b)
}

