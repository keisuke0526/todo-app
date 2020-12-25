package controllers

import javax.inject._
import play.api.mvc._
import ixias.model._

import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global
import lib.model.Category
import lib.persistence.default.{TodoRepository, CategoryRepository}
import model.{ViewValueTodos,ViewValueCategories, ViewValueCategory}

@Singleton
class CategoryController @Inject()(val controllerComponents: ControllerComponents) extends BaseController {

//  def list() = Action.async { implicit request: Request[AnyContent] =>
//    for {
//      categories      <- CategoryRepository.getAll()
//    } yield { 
//      val vv = ViewValueCategories(
//        title = "カテゴリー一覧"
//        cssSrc = Seq("main.css"),
//        jsSrc = Seq("main.js"),
//        categorySeq = categories.map( categories =>
//          ViewValueCategory(
//            categories.id,
//            categories.v.name,
//            categories.v.slug,
//            categories.v.color,
//            categories.v.updatedAt,
//            categories.v.createdAt
//          )
//        )
//      )
//    //Ok(views.html.category.Category(vv))
//    }
//  }
}

