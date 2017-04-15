package io.whitebox.scala.sandbox

object Choose {
  def choose[E](num: Int, first: () => E, second: () => E, third: () => E) =
    if (num == 1) first()
    else if (num == 2) second()
    else if (num == 3) third()

  def simplerChoose[E](num: Int, first: => E, second: => E, third: => E) =
    if (num == 1) first
    else if (num == 2) second
    else if (num == 3) third

  def timeRun[E](toTime: => E) = {
    val start = System.currentTimeMillis
    toTime
    System.currentTimeMillis - start
  }
  
  def avgTime[E](times: Int, toTime: => E) = {
    val allTimes = for (_ <- Range(0, times)) yield timeRun(toTime)
    allTimes.sum / times
  }
}
