package lib.model

import ixias.model._
import ixias.util.EnumStatus

import java.time.LocalDateTime

import Todo._
case class Todo(
  id:       Option[Id],
  title:    String,
  state:    Status,
  content:  String,
  updatedAt: LocalDateTime = NOW,
  createdAt: LocalDateTime = NOW
) extends EntityModel[Id]


// コンパニオンオブジェクト
object Todo{

  val Id = the[Identity[id]]
  type Id = Long @@ Todo
  type WithNoId = Entity.WithNoId [Id, Todo]
  //メモ []の中身Id,Todoは、定義元ではどのように処理しているのか？
  //https://github.com/ixias-net/ixias/blob/635ca0a177892bfd1597a25d8ee203563044a643/framework/ixias-core/src/main/scala/ixias/model/Entity.scala#L46
  type EmbeddedId = Entity.EmbeddedId[Id, Todo]

  sealed abstract class Status(val code: Short, val name: String) extends EnumStatus
  //　メモ EnumStatusはどこから？EnumStatus.Ofとは？
  object Status extends EnumStatus.Of[Status] {
    case object ToDo  extends Status(code = 0, name = "ToDo")
    case object Doing extends Status(code = 1, name = "Doing")
    case object Done  extends Status(code = 2, name = "Done")
  }

  def build(title: String, state: Status, content: String): WithNoId = {
    new Entity.WithNoId(
      new Todo(
        id      = None,
        title   = title,
        content = content
      )
    )
  }
}
