package controllers

import javax.inject._
import play.api.mvc._
import ixias.model._

import scala.concurrent.ExecutionContext.Implicits.global
import lib.model.Todo
import lib.persistence.default.TodoRepository
import model.{ViewValueTodos, ViewValueTodo}
import scala.concurrent.Future



@Singleton
class TodoController @Inject()(val controllerComponents: ControllerComponents) extends BaseController {
  
  def list() = Action { implicit req =>
    for {
      todos <- TodoRepository.getAll()
    } yield {
    val vv = ViewValueTodo(
      title = "カテゴリー一覧",
      cssSrc = Seq("main.css"),
      jsSrc = Seq("main.js"),
      todos = ViewValueTodos(
        
      )
      ) 
    Ok(views.html.Todo(vv))
    }
  }
 

//  def list() = Action { implicit req =>
//    val test = ViewValueTodo(
//      title = "カテゴリー一覧",
//      cssSrc = Seq("main.css"),
//      jsSrc = Seq("main.js")
//    ) 
//    Ok(views.html.Todo(test))
//  }
}
