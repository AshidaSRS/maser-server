//package com.shin.telegram
//
//import cats.effect.Effect
//import com.shin.utils.TelegramIntegration
//import info.mukel.telegrambot4s.api.TelegramBot
//
//class Commands[F[_]: Effect](
//    implicit mangaCommands: MangaCommands[F]
//)
//
//object Commands extends TelegramBot with TelegramIntegration {
//  implicit def instance[F[_] : Effect](implicit mangaCommands: MangaCommands[F]) =
//    new Commands[F]
//}