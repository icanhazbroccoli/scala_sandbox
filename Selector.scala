package io.whitebox.scala.sandbox.selector

import scala.annotation.tailrec
import scala.collection.immutable.Map
import scala.collection.mutable.WrappedArray

object Selector {
  def selector(path: Symbol*): (Map[Symbol, Any] => Option[Any]) = {
    if (path.size <= 0) throw new IllegalArgumentException("path must not be empty")

    @tailrec
    def selectorHelper(path: Seq[Symbol], ds: Map[Symbol, Any]): Option[Any] =
      if (path.size == 1) {
        ds.get(path(0))
      } else {
        val currentPiece = ds.get(path.head)
        currentPiece match {
          case Some(currentMap: Map[Symbol, Any]) =>
            selectorHelper(path.tail, currentMap)
          case None => None
          case _ => None
        }
      }

    (ds: Map[Symbol, Any]) => selectorHelper(path.toSeq, ds)
  }
}
