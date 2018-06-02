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

package com.shin.persistence

import com.shin.Entertainment
import freestyle.tagless.tagless

@tagless(true)
trait EntertainmentRepository[F[_]] {

  def insert(manga: Entertainment): F[Option[Entertainment]]

  def get(id: Long): F[Option[Entertainment]]

  def delete(id: Long): F[Int]

  def update(manga: Entertainment): F[Option[Entertainment]]

  def list: F[List[Entertainment]]

  def getLikeName(name: String): F[List[Entertainment]]

}
