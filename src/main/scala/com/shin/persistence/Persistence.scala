package com.shin.persistence

import freestyle.tagless.module

@module
trait Persistence[F[_]] {
  val mangaRepository: MangaRepository[F]
}
