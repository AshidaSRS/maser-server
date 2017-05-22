package maser

import akka.actor.ActorSystem
import akka.http.scaladsl.model.headers.{Authorization, BasicHttpCredentials, GenericHttpCredentials}
import akka.stream.Materializer
import com.typesafe.scalalogging.Logger
import maser.WebService._
import models._

import scala.concurrent.{ExecutionContext, Future}
import scala.util.Try
/**
  * Created by ashida on 21/05/17.
  */
object MyAnimeListAPI {
  lazy val logger: Logger = Logger("MyAnimeListAPI")
  lazy val animeListUrl: String = com.typesafe.config.ConfigFactory.load().getConfig("myanimelist").getString("account.url").stripSuffix("/")

  def buildUrl(extra: String) = s"$animeListUrl${if (extra.startsWith("/")) extra else "/" + extra }"

  def getUser(u: String, p: String)(implicit system: ActorSystem, materializer: Materializer, execution: ExecutionContext): Future[User] = {
    val header = GenericHttpCredentials(u,p)
    val url = buildUrl(s"/verify_User.xml")
    implicit val transformer: String => Option[User] =
      result => Try((scalaxb.fromXML[User](scala.xml.XML.loadString(result)))).toOption
    logger.info("Asking for User")
    getUrlAkka(url = url, header = Some(header)) recover { case _ => None } map (_.getOrElse(User(0,"")))
  }
}
