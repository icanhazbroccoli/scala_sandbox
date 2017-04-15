package io.whitebox.scala.sandbox.memoization

object Memoization {
  def expensiveLookup(id: Int) = {
    Thread.sleep(1000)
    println(s"Doing expensive lookup for $id")
    Map(42 -> "foo", 12 -> "bar", 1 -> "baz").get(id)
  }

  def memoizeExpensiveLookup() = {
    var cache = Map[Int, Option[String]]()
    (id: Int) =>
      cache.get(id) match {
        case Some(result: Option[String]) => result
        case None => {
          val result = expensiveLookup(id)
          cache += id -> result
          result
        }
      }
  }
  val memoizedExpensiveLookup = memoizeExpensiveLookup
}
