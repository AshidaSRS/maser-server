

import cats.effect.{Effect, IO}
import cats.syntax.either._
import cats.syntax.flatMap._
import cats.syntax.functor._
import com.shin.http.Api
import com.shin.persistence.Persistence
import com.shin.services.Services
import doobie.util.transactor.Transactor
import freestyle.tagless.config.ConfigM
import freestyle.tagless.config.implicits._
import freestyle.tagless.effects.error.ErrorM
import freestyle.tagless.effects.error.implicits._
import freestyle.tagless.logging.LoggingM
import freestyle.tagless.loggingJVM.log4s.implicits._
import freestyle.tagless.module
import fs2.StreamApp
import org.http4s.HttpService
import org.http4s.implicits._
import org.http4s.server.blaze.BlazeBuilder

@module
trait App[F[_]] {
  val persistence: Persistence[F]
  val services: Services[F]
}

object MainApp extends StreamApp[IO] {

  import com.shin.runtime.implicits._

  override def stream(
      args: List[String],
      requestShutdown: IO[Unit]): fs2.Stream[IO, StreamApp.ExitCode] =
    bootstrap[IO].unsafeRunSync()

  def bootstrap[F[_]: Effect](
      implicit app: App[F],
      T: Transactor[F],
      api: Api[F]): F[fs2.Stream[F, StreamApp.ExitCode]] = {

    val services: HttpService[F] = api.endpoints
    val log: LoggingM[F] = app.services.log
    val config: ConfigM[F] = app.services.config

    for {
      _ <- log.info("Trying to load application.conf")
      cfg <- config.load
      host: String = cfg.string("http.host").getOrElse("localhost")
      port: Int = cfg.int("http.port").getOrElse(8080)
      _ <- log.debug(s"Host: $host")
      _ <- log.debug(s"Port: $port")
    } yield
      BlazeBuilder[F]
        .bindHttp(port, host)
        .mountService(services)
        .serve
  }
}
