package lib.model

import ixias.model._
import ixias.util.EnumStatus

import java.time.LocalDateTime

import Category._
case class Category(
  id:         Option[Id],
  name:       String,
  slug:       String,
  color:      CategoryColor,
  updatedAt:  LocalDateTime = NOW,
  createdAt:  LocalDateTime = NOW
) extends EntityModel[Id]

object Category{

  val Id  = the[Identity[Id]]
  type Id = Long @@ Category
  type WithNoId   = Entity.WithNoId [Id, Category]
  type EmbeddedId = Entity.EmbeddedId[Id, Category]

 
  sealed abstract class CategoryColor(val code: Short) extends EnumStatus
  object CategoryColor extends EnumStatus.Of[CategoryColor] {
    case object BLUE extends CategoryColor(code = 0)
    case object RED extends CategoryColor(code = 1)
    case object YELLO extends CategoryColor(code = 2)
  }

  def build(name: String, slug: String, color: CategoryColor): WithNoId = {
    new Entity.WithNoId(
      new Category(
        id    = None,
        name  = name,
        slug  = slug,
        color = color
      )
    )
  }
}
