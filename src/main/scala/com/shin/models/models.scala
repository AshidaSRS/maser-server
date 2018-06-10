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

package com.shin

import java.sql.Timestamp

sealed abstract class Entity extends Product with Serializable {
  def id: Option[Long]
  def created: Option[Timestamp]
}

final case class Entertainment(
    name: String,
    rate: Int,
    model: String,
    id: Option[Long] = None,
    created: Option[Timestamp] = None,
    updated: Option[Timestamp] = None
) extends Entity

final case class User(
    alias: String,
    telegramId: Long,
    id: Option[Long] = None,
    created: Option[Timestamp] = None,
    updated: Option[Timestamp] = None
) extends Entity

final case class UserContent(
    userId: Long,
    entertainmentId: Long,
    id: Option[Long],
    created: Option[Timestamp] = None
) extends Entity

final case class Recommendation(
    recommenderId: Long,
    entertainmentId: Long,
    recommendedId: Long,
    id: Option[Long],
    created: Option[Timestamp] = None,
    updated: Option[Timestamp] = None
) extends Entity
