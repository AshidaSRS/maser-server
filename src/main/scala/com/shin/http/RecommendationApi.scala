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
package http

import cats.effect.Effect
import cats.implicits._
import com.shin.services.RecommendationService
import io.circe.generic.auto._
import io.circe.syntax._
import org.http4s._
import org.http4s.circe._
import org.http4s.dsl.Http4sDsl

class RecommendationApi[F[_]: Effect](
    implicit service: RecommendationService[F])
    extends Http4sDsl[F] {

  import codecs._

  private val prefix: String = "recommendations"

  object RecommenderQueryParamMatcher
      extends QueryParamDecoderMatcher[Long]("recommender")
  object RecommendedQueryParamMatcher
      extends QueryParamDecoderMatcher[Long]("recommended")
  object EntertainmentQueryParamMatcher
      extends QueryParamDecoderMatcher[Long]("entertainment")

  //:? NameQueryParamMatcher(name)

  val endpoints: HttpService[F] = HttpService[F] {
    case GET -> Root / `prefix` / LongVar(id) =>
      service.retrieve(id) flatMap { item =>
        item.fold(NotFound(None.asJson))(Recommendation =>
          Ok(Recommendation.asJson))
      }

    case GET -> Root / `prefix` :? RecommenderQueryParamMatcher(
          recommenderId) =>
      service.listByRecommenderId(recommenderId).flatMap(l => Ok(l.asJson))

    case GET -> Root / `prefix` :? RecommendedQueryParamMatcher(
          recommendedId) =>
      service.listByRecommendedId(recommendedId).flatMap(l => Ok(l.asJson))

    case GET -> Root / `prefix` :? EntertainmentQueryParamMatcher(
          entertainmentId) =>
      service.listByEntertainmentId(entertainmentId).flatMap(l => Ok(l.asJson))

    case GET -> Root / `prefix` :? RecommenderQueryParamMatcher(recommenderId) +& RecommendedQueryParamMatcher(
          recommendedId) =>
      service.listByIds(recommenderId, recommendedId).flatMap(l => Ok(l.asJson))

    case GET -> Root / `prefix` =>
      service.list.flatMap(l => Ok(l.asJson))

    case req @ POST -> Root / `prefix` =>
      for {
        recommendation <- req.as[Recommendation]
        insertedRecommendation <- service.insert(recommendation)
        response <- Ok(insertedRecommendation.asJson)
      } yield response

    case req @ PUT -> Root / `prefix` / LongVar(id) =>
      for {
        recommendation <- req.as[Recommendation]
        updatedRecommendation <- service.update(
          recommendation.copy(id = Some(id)))
        reponse <- Ok(updatedRecommendation.asJson)
      } yield reponse

    case DELETE -> Root / `prefix` / LongVar(id) =>
      service.destroy(id) *> Ok()
  }
}

object RecommendationApi {

  implicit def instance[F[_]: Effect](
      implicit service: RecommendationService[F]): RecommendationApi[F] =
    new RecommendationApi[F]
}
