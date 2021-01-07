package model

import ixias.model._
import ixias.util.EnumStatus
import lib.model.Todo
import lib.model.Todo.Status
import java.time.LocalDateTime
import controllers.TodoFormData
import play.api.data._
import play.api.data.Forms._


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

case class ViewValueTodoAdd(
  title:    String,
  cssSrc:   Seq[String],
  jsSrc:    Seq[String],
  todoForm: Form[TodoFormData]
) extends ViewValueCommon

case class ViewValueTodoEdit(   
  id:       Long,
  title:    String,
  cssSrc:   Seq[String],
  jsSrc:    Seq[String],
  todoForm: Form[TodoFormData]
) extends ViewValueCommon
