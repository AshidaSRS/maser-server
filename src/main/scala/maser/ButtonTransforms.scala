package maser

import info.mukel.telegrambot4s.models.InlineKeyboardButton
import models._

object ButtonTransforms {
  trait ButtonImplicits[T] {
    def toURLButton(b: T, a: URL): T
    def toCallBakcButton(b: T, a: CallBack): T
    def toQueryButton(b: T, a: Query): T
    def toQueryChatButton(b: T, a: QueryChat): T
  }

  object ButtonImplicits {

    implicit class URLTrans(b: InlineKeyboardButton) {
      def toURLButton(s: URL): InlineKeyboardButton =
        b.copy(url = Some(s.a))
    }

    implicit class CallBackTrans(b: InlineKeyboardButton) {
      def toCallBakcButton(s: CallBack): InlineKeyboardButton =
        b.copy(callbackData = Some(s.a))
    }

    implicit class QueryTrans(b: InlineKeyboardButton) {
      def toQueryButton(s: Query): InlineKeyboardButton =
        b.copy(switchInlineQuery = Some(s.a))
    }

    implicit class QueryChatTrans(b: InlineKeyboardButton) {
      def toQueryChatButton(s: QueryChat): InlineKeyboardButton =
        b.copy(switchInlineQueryCurrentChat = Some(s.a))
    }

  }
}
