import cats.Monad
import cats.implicits._
import freestyle.tagless._
import freestyle.tagless.logging.LoggingM
import com.shin.db.models.Manga
import com.shin.db.persistence.MangaRepository

@module
trait MangaService[F[_]] {

  implicit val M: Monad[F]
  implicit val L: LoggingM[F]

  val repo: MangaRepository[F]

  val model: String = classOf[Manga].getSimpleName

  def insert(item: Manga): F[Option[Manga]] =
    for {
      _            <- L.debug(s"Trying to insert a $model")
      insertedItem <- repo.insert(item)
      _            <- L.info(s"Tried to add $model")
    } yield insertedItem

  def retrieve(id: Long): F[Option[Manga]] =
    for {
      _    <- L.debug(s"Trying to retrieve a $model")
      item <- repo.get(id)
      _    <- L.info(s"Found $model: $item")
    } yield item

  def update(item: Manga): F[Option[Manga]] =
    for {
      _           <- L.debug(s"Trying to update a $model")
      updatedItem <- repo.update(item)
      _           <- L.info(s"Tried to update $model")
    } yield updatedItem

  def destroy(id: Long): F[Int] =
    for {
      _            <- L.debug(s"Trying to delete a $model")
      deletedItems <- repo.delete(id)
      _            <- L.info(s"Tried to delete $model")
    } yield deletedItems

  def batchedInsert(items: List[Manga]): F[List[Option[Manga]]] =
    for {
      _             <- L.debug(s"Trying to insert batch $model")
      insertedItems <- items.traverse(repo.insert)
    } yield insertedItems

  def batchedUpdate(items: List[Manga]): F[List[Option[Manga]]] =
    for {
      _            <- L.debug(s"Trying to update batch $model")
      updatedItems <- items.traverse(repo.update)
    } yield updatedItems

  def batchedDestroy(ids: List[Int]): F[Int] =
    for {
      _              <- L.debug(s"Trying to destroy batch $model")
      destroyedItems <- ids.traverse(repo.delete)
    } yield destroyedItems.sum

  val list: F[List[Manga]] =
    for {
      _     <- L.debug(s"Trying to get all $model models")
      items <- repo.list
      _     <- L.info(s"Found all $model models")
    } yield items
}
