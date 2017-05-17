package maser

import info.mukel.telegrambot4s.Implicits._
import info.mukel.telegrambot4s.models._
import info.mukel.telegrambot4s.api._
import com.typesafe.scalalogging.Logger
import UtilsCommand._


trait Methods extends TelegramBot with Polling with Commands with UtilsCommand{

  type InlineKeyFile = List[InlineKeyboardButton]

  def simpleCommand(c: String, desc: String, response: String, pagePreview: Option[Boolean] = None,
    notification: Option[Boolean] = None, replyToId: Option[Long] = None) {
    val logger: Logger = Logger("Simple")
    val order = s"/$c"
    on(order, desc) {
      implicit msg => args => {
        logger.debug(msg.chat.id.toString)
        logger.info(s"$order answering: $response")
        reply(response, None, pagePreview, notification, replyToId, None)
      }
    }
  }

  def inlineBoardCommand(c: String, desc: String, response: String, board: InlineKeyboardMarkup) = {
    val logger: Logger = Logger("InlineKeyboard")
    val order = s"/$c"
    on(order, desc) {
      implicit msg => args => {
        logger.debug(msg.chat.id.toString)
        logger.info(s"$order answering: $response")
        reply(response, None,None,None,None,board)
      }
    }
  }

  def genInlineButton(t: String, tag: String): InlineKeyboardButton = {
    tag match {
      case x if x.startsWith("url:") =>
        InlineKeyboardButton(
          text = t,
          callbackData = None,
          url = unTag("url:", x),
          switchInlineQuery = None,
          switchInlineQueryCurrentChat = None,
          callbackGame = None
        )
      case x if x.startsWith("callback:") =>
        InlineKeyboardButton(
          text = t,
          callbackData = unTag("callback:" ,x),
          url = None,
          switchInlineQuery = None,
          switchInlineQueryCurrentChat = None,
          callbackGame = None
        )
      case x if x.startsWith("query:") =>
        InlineKeyboardButton(
          text = t,
          callbackData = None,
          url = None,
          switchInlineQuery = unTag("query:" ,x),
          switchInlineQueryCurrentChat = None,
          callbackGame = None
        )
      case x if x.startsWith("querychat:") =>
        InlineKeyboardButton(
          text = t,
          callbackData = None,
          url = None,
          switchInlineQuery = None,
          switchInlineQueryCurrentChat = unTag("querychat:" ,x),
          callbackGame = None
        )
      // case x if x.startsWith("game:") =>
      //   InlineKeyboardButton(
      //     text = t,
      //     callbackData = None,
      //     url = None,
      //     switchInlineQuery = None,
      //     switchInlineQueryCurrentChat = None,
      //     callbackGame = unTagGame("game:" ,x)
      //   )
      case x if x.startsWith("some:") =>
        getButtonParam(unTag("some:", x))
        InlineKeyboardButton(
          text = t,
          callbackData = None,
          url = None,
          switchInlineQuery = None,
          switchInlineQueryCurrentChat = None,
          callbackGame = None
        )
  }
}

  def genInlineKeyboard(l: List[InlineKeyFile]): InlineKeyboardMarkup = {
    InlineKeyboardMarkup(inlineKeyboard = l)
  }
}

case class MaserCommands(token: String) extends Methods{
  simpleCommand("test", "test", "test")
  simpleCommand("test2", "test2", "test2")
  inlineBoardCommand("testin", "testin", "testin",
    genInlineKeyboard(List(
      List(
        genInlineButton("test", "url:https://acmupm.es"),
        genInlineButton("test2", "url:https://notengoenie.com")
      ),
      List(
        genInlineButton("test2L", "some:url::https://google.com::callback::asdfsad")
      )
    )))
}
