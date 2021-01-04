package lib.persistence.db

import java.time.LocalDateTime
import slick.jdbc.JdbcProfile
import ixias.persistence.model.Table
import lib.model.Category 
import lib.model.Todo
import lib.model.Todo.Status


//Todoテーブルへのマッピングを行う
case class TodoTable[P <: JdbcProfile]()(implicit val driver: P)
  extends Table[Todo, P] {
  import api._

  //DataSourceNameの定義
  lazy val dsn = Map(
    "master" -> DataSourceName("ixias.db.mysql://master/to_do"),
    "slave"  -> DataSourceName("ixias.db.mysql://slave/to_do")
  )
  
  //queryの定義
  class Query extends BasicQuery(new Table(_)) {}
  lazy val query = new Query

  //Tableの定義
  class Table(tag: Tag) extends BasicTable(tag, "to_do") {
    import Todo._
    // Columns
    /* @1 */ def id              = column[Id]            ("id",           O.UInt64, O.PrimaryKey, O.AutoInc)
    //カテゴリーと紐つけたらCategory.Id型に変更
    /* @2 */ def categoryId     = column[Long]          ("categoryId",  O.UInt64)
    /* @3 */ def title           = column[String]        ("title",        O.Utf8Char255)
    /* @4 */ def content         = column[String]        ("content",      O.Utf8Char255)
    /* @5 */ def state           = column[Short]        ("state",        O.UInt8)
    /* @6 */ def updatedAt       = column[LocalDateTime] ("updated_at",   O.TsCurrent)
    /* @7 */ def createdAt       = column[LocalDateTime] ("created_at",   O.Ts)
 

    type TableElementTuple = (
      Option[Id], Long, String, String, Short, LocalDateTime, LocalDateTime
    )

  // DB <=> Scalaの相互のマッピングの定義
  //category_idの型変更すること
    def * = (id.?, categoryId, title, content, state, updatedAt, createdAt) <> (
      // Tuple(table) => Model
      (t: TableElementTuple) => Todo(
        t._1, t._2, t._3, t._4, t._5, t._6, t._7
      ),
      // Model => Tuple(table)
      (v: TableElementType) => Todo.unapply(v).map { t => (
        t._1, t._2, t._3, t._4, t._5, LocalDateTime.now(), t._7
      )}
    )
  }
}
