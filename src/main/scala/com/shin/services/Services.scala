package com.shin
package services

import com.shin.services._
import freestyle.tagless.config.ConfigM
import freestyle.tagless.logging.LoggingM
import freestyle.tagless.module

@module
trait Services[F[_]] {
  val mangaService: MangaService[F]
}
