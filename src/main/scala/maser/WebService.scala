package maser

import akka.http.scaladsl.Http
import akka.http.scaladsl.model._
import akka.actor.ActorSystem
import akka.http.scaladsl.model.headers.GenericHttpCredentials
import akka.http.scaladsl.server.directives.Credentials
import akka.stream.Materializer
import com.sun.net.httpserver.BasicAuthenticator

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future
import scala.concurrent.duration._

object WebService {

  def getUrlAkka[T](url: String, timeout: FiniteDuration = 15 seconds, header: Option[GenericHttpCredentials] = None)(implicit parser: String => Option[T], system: ActorSystem, materializer: Materializer): Future[Option[T]] = {
    header match {
      case None =>
        Http().singleRequest(HttpRequest(uri = url)).flatMap(resp => {
          if (resp.status.isFailure()) {
            Future.successful(None)
          } else {
            val bodyF = resp.entity.toStrict(timeout) map {_.data} map { _.utf8String }
            bodyF.map(body => parser(body))
          }
        })
      case Some(h) =>
        Http().singleRequest(HttpRequest(uri = url).addCredentials(h)).flatMap(resp => {
          if (resp.status.isFailure()) {
            Future.successful(None)
          } else {
            val bodyF = resp.entity.toStrict(timeout) map {_.data} map { _.utf8String }
            bodyF.map(body => parser(body))
          }
        })
    }
  }

}