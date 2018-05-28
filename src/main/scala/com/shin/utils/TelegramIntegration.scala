package com.shin.utils

import wvlet.log.Logger

import scala.io.Source

trait TelegramIntegration {

  lazy val token: String = scala.util.Properties
    .envOrNone("BOT_TOKEN")
    .getOrElse(Source.fromFile("bot.token").getLines().mkString)

  lazy val log = Logger("TelegramIntegration")
}
