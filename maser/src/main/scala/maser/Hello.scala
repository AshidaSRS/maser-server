package maser

import akka.actor.ActorSystem
import akka.stream.{ActorMaterializer, Materializer}
import com.typesafe.scalalogging.Logger

import scala.concurrent.ExecutionContext
import scala.concurrent.ExecutionContext.global

object Hello extends App {
  implicit val token = "309059165:AAEpzBsbq_BtYDLBsefOFApiqu7ouoglAxE"
  implicit val actorSystem = ActorSystem("maser")
  implicit val materializer: Materializer = ActorMaterializer()
  implicit val executionContext: ExecutionContext = global

  val logger = Logger("Main")
  logger.info("Starting bot")
  implicit val methods: MaserCommands  = MaserCommands(token)
  MyAnimeListAPI.getUser("AshidaSRS", "02121992").map(x => println(s"${x.id} for ${x.username}"))
  methods.run()
}
