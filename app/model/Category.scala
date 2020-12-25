package model

import ixias.model._
import ixias.util.EnumStatus
import lib.model.Category
import lib.model.Category.CategoryColor
import java.time.LocalDateTime
import lib.persistence.CategoryRepository

case class ViewValueCategories(
  title:  String,
  cssSrc: Seq[String],
  jsSrc:  Seq[String],
  categorySeq:  Seq[ViewValueCategory]
) extends ViewValueCommon


case class ViewValueCategory(
  id: Category.Id,
  name: String,
  slug: String,
  color: CategoryColor,
  updatedAt: LocalDateTime = NOW,
  createdAt: LocalDateTime = NOW
)
