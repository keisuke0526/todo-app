package lib.model

import ixias.model._
import ixias.util.EnumStatus

import java.time.LocalDateTime

import Todo._
case class Todo(
  id:          Option[Id],
  //category_idの型変更すること
  category_id: Long,
  title:       String,
  content:     String,
  state:       Short,
  updatedAt:   LocalDateTime = NOW,
  createdAt:   LocalDateTime = NOW
) extends EntityModel[Id]


// コンパニオンオブジェクト
object Todo{

  val Id = the[Identity[Id]]
  type Id = Long @@ Todo
  type WithNoId = Entity.WithNoId [Id, Todo]
  type EmbeddedId = Entity.EmbeddedId[Id, Todo]

  sealed abstract class Status(val code: Short, val name: String) extends EnumStatus
  object Status extends EnumStatus.Of[Status] {
    case object Todo  extends Status(code = 0, name = "着手前")
    case object Doing extends Status(code = 1, name = "進行中")
    case object Done  extends Status(code = 2, name = "完了")
  }

  def apply(category_id: Long, title: String, content: String, state: Short): WithNoId = {
    new Entity.WithNoId(
      new Todo(
        id          = None,
        category_id = category_id,
        title       = title,
        content     = content,
        state       = state
      )
    )
  }
}

