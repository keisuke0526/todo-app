package controllers

import javax.inject._
import play.api.mvc._
import ixias.model._

import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global
import lib.model.{Todo, Category}
import lib.persistence.default.{TodoRepository, CategoryRepository}
import model.{ViewValueTodos, ViewValueCategory}
// ViewValueTodoAdd
import play.api.data._
import play.api.data.Forms._
import ixias.util.EnumStatus
import lib.model.Todo.Status



//case class TodoFormatDate(category_id: Category.Id, title: String, content: String, state: Status)

@Singleton
class TodoController @Inject()(val controllerComponents: ControllerComponents) extends BaseController {
 //メモ DBへアクセスして、データを返す場合には、Action.asyncというビルダーメソッドを使う。"https://www.playframework.com/documentation/ja/2.3.x/ScalaAsync"参照 
  def list() = Action.async { implicit request: Request[AnyContent] =>
    for {
      todos      <- TodoRepository.getAll()
      //categories <- CategoryRepository.getAll()
    } yield { 
    val vv = ViewValueTodos(
      title = "タスク一覧",
      cssSrc = Seq("main.css"),
      jsSrc = Seq("main.js"),
      todoSeq =todos 
    )
    Ok(views.html.todo.Todo(vv))
    }
  }

 // val form = Form(
 //   mapping(
 //     "category_id" -> category_id,
 //     "title"       -> title,
 //     "content"     -> content,
 //     "state"       -> state
 //   )(TodoFormatDate.apply)(TodoFormatDate.unapply)
 //)

  //def register() = Action.async { implicit request: Request[AnyContent] =>
   // val vv = ViewValueTodoAdd(
//      title = "新規登録",
//      cssSrc = Seq("main.css"),
//      jsSrc = Seq("main.js"),
//      form = form
//    )
//   Ok(views.html.todo.Store(vv))
  //}

  
}
