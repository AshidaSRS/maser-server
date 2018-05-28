
//package com.shin.telegram
//
//import cats.effect.Effect
//import cats.implicits._
//import com.shin.services.MangaService
//import com.shin.utils.TelegramIntegration
//import info.mukel.telegrambot4s.api.{Polling, TelegramBot}
//import info.mukel.telegrambot4s.api.declarative.{Callbacks, Commands}
//
//class MangaCommands[F[_]: Effect](implicit service: MangaService[F])  extends TelegramBot with  Polling
//  with Commands with Callbacks with TelegramIntegration {
//
//  onCommand('list_manga) { implicit msg =>
//    service.list.map(l => reply(l.mkString(",")))
//  }
//}