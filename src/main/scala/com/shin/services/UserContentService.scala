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

package com.shin.services

import cats.Monad
import cats.implicits._
import com.shin.UserContent
import com.shin.persistence.UserContentRepository
import freestyle.tagless._
import freestyle.tagless.logging.LoggingM

@module
trait UserContentService[F[_]] {

  implicit val M: Monad[F]
  implicit val L: LoggingM[F]

  val repo: UserContentRepository[F]

  val model: String = classOf[UserContent].getSimpleName

  def insert(item: UserContent): F[Option[UserContent]] =
    for {
      _ <- L.debug(s"Trying to insert a $model")
      insertedItem <- repo.insert(item)
      _ <- L.info(s"Tried to add $model")
    } yield insertedItem

  def retrieve(id: Long): F[Option[UserContent]] =
    for {
      _ <- L.debug(s"Trying to retrieve a $model")
      item <- repo.get(id)
      _ <- L.info(s"Found $model: $item")
    } yield item

  def update(item: UserContent): F[Option[UserContent]] =
    for {
      _ <- L.debug(s"Trying to update a $model")
      updatedItem <- repo.update(item)
      _ <- L.info(s"Tried to update $model")
    } yield updatedItem

  def destroy(id: Long): F[Int] =
    for {
      _ <- L.debug(s"Trying to delete a $model")
      deletedItems <- repo.delete(id)
      _ <- L.info(s"Tried to delete $model")
    } yield deletedItems

  def batchedInsert(items: List[UserContent]): F[List[Option[UserContent]]] =
    for {
      _ <- L.debug(s"Trying to insert batch $model")
      insertedItems <- items.traverse(repo.insert)
    } yield insertedItems

  def batchedUpdate(items: List[UserContent]): F[List[Option[UserContent]]] =
    for {
      _ <- L.debug(s"Trying to update batch $model")
      updatedItems <- items.traverse(repo.update)
    } yield updatedItems

  def batchedDestroy(ids: List[Long]): F[Int] =
    for {
      _ <- L.debug(s"Trying to destroy batch $model")
      destroyedItems <- ids.traverse(repo.delete)
    } yield destroyedItems.sum

  val list: F[List[UserContent]] =
    for {
      _ <- L.debug(s"Trying to get all $model models")
      items <- repo.list
      _ <- L.info(s"Found all $model models")
    } yield items

  def listByUserId(userId: Long): F[List[UserContent]] =
    for {
      _ <- L.debug(s"Try to get all $model models with userId = $userId")
      items <- repo.listByUserId(userId)
      _ <- L.info(s"Found all $model models for userId = $userId")
    } yield items
}
