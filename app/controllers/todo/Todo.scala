package controllers

import javax.inject._
import play.api.mvc._
import ixias.model._

import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global
import lib.model.{Todo, Category}
import lib.persistence.default.{TodoRepository, CategoryRepository}
import model.{ViewValueTodos, ViewValueTodoAdd}
import play.api.data._
import play.api.data.Forms._
import ixias.util.EnumStatus
import lib.model.Todo.Status
import play.api.i18n.I18nSupport 


case class TodoFormData(category_id: Int, title: String, content: String, state: Status)

@Singleton
class TodoController @Inject()(val controllerComponents: ControllerComponents) extends BaseController with I18nSupport {
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
    Ok(views.html.todo.List(vv))
    }
  }

  val todoForm: Form[TodoFormData] = Form(
    mapping(
      "category_id" -> number,
      "title"       -> nonEmptyText,
      "content"     -> text,
      "state"       -> number 
    )(TodoFormData.apply)(TodoFormData.unapply))


  def register() = Action{ implicit request: Request[AnyContent] =>
    val vv = ViewValueTodoAdd(
      title = "新規登録",
      cssSrc = Seq("main.css"),
      jsSrc = Seq("main.js"),
      todoForm = todoForm
    )
    Ok(views.html.todo.Register(vv))
  }

  def store() = Action{ implicit request: Request[AnyContent] => 
    todoForm.bindFromRequest().fold(
      (formWithErrors: Form[TodoFormData]) => {
        val vv = ViewValueTodoAdd(
          title = "新規登録",
          cssSrc = Seq("main.css"),
          jsSrc = Seq("main.js"),
          todoForm = formWithErrors
        )
        BadRequest(views.html.todo.Register(vv))
      },
      (todoForm: TodoFormData) => {  
      val recievedForm = (
        todoForm.category_id,
        todoForm.title,
        todoForm.content,
        todoForm.state
      )      
      println(recievedForm)
//        for {
//          todoWithNoId <- Todo.apply(recievedForm)
//          _ <- TodoRepository.add(todoWithNoId)
//        } yield {
        Redirect(routes.TodoController.list)
//        }
      }
    )
  } 
}
