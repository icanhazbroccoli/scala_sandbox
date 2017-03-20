package io.whitebox.sandbox.tail_rec
import scala.util.control.TailCalls._

object EvenOdd {
  def isOddTrampoline(n: Long): TailRec[Boolean] =
    if (n == 0) done(false) else tailcall(isEvenTrampoline(n - 1))

  def isEvenTrampoline(n: Long): TailRec[Boolean] =
    if (n == 0) done(true) else tailcall(isOddTrampoline(n - 1))
}
