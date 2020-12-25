package lib.persistence

import scala.concurrent.Future
import ixias.persistence.SlickRepository
import lib.model.Category
import slick.jdbc.JdbcProfile

case class CategoryRepository[P <: JdbcProfile]()(implicit val driver: P)
  extends SlickRepository[Category.Id, Category, P]
  with db.SlickResourceProvider[P] {

  import api._

  def getAll(): Future[Seq[EntityEmbeddedId]] =
    RunDBAction(CategoryTable, "slave") { slick =>
      slick.result
    }

  def get(id: Id): Future[Option[EntityEmbeddedId]] =
    RunDBAction(CategoryTable, "slave") { _
      .filter(_.id === id)
      .result.headOption
    }

  //Add Category Data 
  def add(entity: EntityWithNoId): Future[Id] =
    RunDBAction(CategoryTable) { slick =>
      slick returning slick.map(_.id) += entity.v
    }

  
  // Update Category Data 
  def update(entity: EntityEmbeddedId): Future[Option[EntityEmbeddedId]] =
    RunDBAction(CategoryTable) { slick =>
      val row = slick.filter(_.id === entity.id)
      for {
        old <- row.result.headOption
        _   <- old match {
          case None    => DBIO.successful(0)
          case Some(_) => row.update(entity.v)
        }
      } yield old
    }

  // Delete Category Data
  def remove(id: Id): Future[Option[EntityEmbeddedId]] =
    RunDBAction(CategoryTable) { slick =>
      val row = slick.filter(_.id === id)
      for {
        old <- row.result.headOption
        _   <- old match {
          case None    => DBIO.successful(0)
          case Some(_) => row.delete
        }
      } yield old
    }
}

