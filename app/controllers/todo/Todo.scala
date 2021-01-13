package controllers

import javax.inject._
import play.api.mvc._
import ixias.model._

import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global
import lib.model.Todo
import lib.persistence.default.TodoRepository
import model.{ViewValueTodos, ViewValueTodoAdd, ViewValueTodoEdit}
import play.api.data._
import play.api.data.Forms._
import ixias.util.EnumStatus
import lib.model.Todo.Status
import play.api.i18n.I18nSupport 


case class TodoFormData(title: String, content: String, state: Short)

@Singleton
class TodoController @Inject()(val controllerComponents: ControllerComponents) extends BaseController with I18nSupport {
 //メモ DBへアクセスして、データを返す場合には、Action.asyncというビルダーメソッドを使う。"https://www.playframework.com/documentation/ja/2.3.x/ScalaAsync"参照 
 
 //一覧表示
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
      "title"       -> nonEmptyText,
      "content"     -> text,
      "state"       -> shortNumber(min = 0, max = 100)
    )(TodoFormData.apply)(TodoFormData.unapply))

  //登録画面
  def register() = Action{ implicit request: Request[AnyContent] =>
    val vv = ViewValueTodoAdd(
      title = "新規登録",
      cssSrc = Seq("main.css"),
      jsSrc = Seq("main.js"),
      todoForm = todoForm
    )
    Ok(views.html.todo.Register(vv))
  }

  //追加機能
  def store() = Action.async{ implicit request: Request[AnyContent] => 
    todoForm.bindFromRequest().fold(
      (formWithErrors: Form[TodoFormData]) => {
        val vv = ViewValueTodoAdd(
          title = "新規登録",
          cssSrc = Seq("main.css"),
          jsSrc = Seq("main.js"),
          todoForm = formWithErrors
        )
        Future.successful(BadRequest(views.html.todo.Register(vv)))
      },
      (todoForm: TodoFormData) => {  
      println(todoForm) // TodoFormData(1,テスト,テストです,1)
      val todoWithNoId = Todo.apply(
        todoForm.title,
        todoForm.content,
        todoForm.state
      ) 
      println(todoWithNoId)
        for {
          _ <- TodoRepository.add(todoWithNoId)
        } yield {
        Redirect(routes.TodoController.list)
        }
      }
    )
  } 

  //編集画面
//  def edit(id: Long) = Action.async {implicit request: Request[AnyContent] =>
//    val todoId = Todo.Id(id)
//    for {
//      todos <- TodoRepository.get(todoId)
//    } yield {
//      todos match {
//        case Some(todos) => 
//          val vv = ViewValueTodoEdit(
//            id = id,
//            title = "Todo編集",
//            cssSrc = Seq("main.css"),
//            jsSrc = Seq("main.js"),
//            todoForm = todoForm.fill(
//              TodoFormData(
//                todos.v.title,
//                todos.v.content,
//                todos.v.state
//              )
//            )
//          )
//          Ok(views.html.todo.Edit(vv))
//        case None        =>
//          Redirect(routes.TodoController.list())
//      }
//    }
//  }
//  def update() = Action.async {implicit request: Request[AnyContent] =>
//    todoForm.bindFromRequest().fold(
//      (formWithErrors: Form[TodoFormData]) => {
//        val vv = ViewValueTodoAdd(
//          title = "新規登録",
//          cssSrc = Seq("main.css"),
//          jsSrc = Seq("main.js"),
//          todoForm = formWithErrors
//        )
//        Future.successful(BadRequest(views.html.todo.Register(vv)))
//      },
//      (todoForm: TodoFormData) => {  
//        //TODO: lib/model/Todoのインスタンスを作成しても、not found: value idのエラー。importはしている。
//        val todoEmbddedId = Todo(
//          id = Some(Todo.Id(id)),
//          title = todoForm.title,
//          content = todoForm.content,
//          state = todoForm.state
//        ).toEmbeddedId
//        for {
//          todoUpdate <- TodoRepository.update(todoEmbddedId)
//        } yield {
//          todoUpdate match {
//          case Some(_) =>
//           //TODO：routes.TodoController.edit(id))だとエラー
//            Redirect(routes.TodoController.list()) 
//          case Some(_) =>
//            Redirect(routes.TodoController.list())
//            }
//        }
//      }
//    )
//  }
}
