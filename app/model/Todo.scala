package model

import ixias.model._
import ixias.util.EnumStatus
import lib.model.{Todo, Category}
import lib.model.Todo.Status
import java.time.LocalDateTime


case class ViewValueTodos(
  title:    String,
  cssSrc:   Seq[String],
  jsSrc:    Seq[String],
  todoSeq:  Seq[lib.model.Todo.EmbeddedId]
 // todoSeq:  Seq[ViewValueTodo]
) extends ViewValueCommon

//case class ViewValueTodo(        
//  id:          Todo.Id,
//  category_id: Category.Id,
//  title:       String,
//  content:     String,
//  state:       Status,
//  updatedAt:   LocalDateTime,
//  createdAt:   LocalDateTime
//) 

//case class ViewValueTodoAdd(
//  title:  String,
//  cssSrc: Seq[String],
//  jsSrc:  Seq[String],
//  form:   TodoFormatDate
//) extends ViewValueCommon
//

