/*
 * Copyright 2017-2018 47 Degrees, LLC. <http://www.47deg.com>
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

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