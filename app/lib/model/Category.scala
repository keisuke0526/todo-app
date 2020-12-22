//package lib.model
//
//import ixias.model._
//import ixias.util.EnumStatus
//
//import java.time.LocalDateTime
//
//import Category._
//case class Category(
//  id:         Option[Id],
//  name:       String,
//  slug:       String,
//  color:      Color,
//  updatedAt:  LocalDateTime = NOW,
//  createdAt:  LocalDateTime = NOW
//) extends EntityModel[Id]
//
//object Category{
//
//  val Id  = the[Identity[Id]]
//  type Id = Long @@ Todo
//  type WithNoId   = Entity.WithNoId [Id, Category]
//  type EmbeddedId = Entity.EmbeddedId[Id, Category]

//メモ　カテゴリ設定されている色が反映がわからない
//  sealed abstract class Color(val code: Short, val color: String) extends EnumStatus
//  object Color extends EnumStatus.Of[Color] {
//    case object Blue extends Color(code = 0, color = "")
//  }
//}
