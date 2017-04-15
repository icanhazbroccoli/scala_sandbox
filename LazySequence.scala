package io.whitebox.scala.sandbox.lazy_sequence

object LazySequence {

  def pagedSequence(pageNum: Int): Stream[String] =
    getPage(pageNum) match {
      case Some(page: String) => page #:: pagedSequence(pageNum + 1)
      case None => Stream.Empty
    }

  def getPage(page: Int) =
    page match {
      case 1 => Some("Page 1")
      case 2 => Some("Page 2")
      case 3 => Some("Page 3")
      case _ => None
    }
}
