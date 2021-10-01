
object ForLoops {
  def main(args: Array[String]) {
    for (i <- 1 to 5) {
      println(s"i using to  $i")
    }
    for (i <- 1.to(5)) {
      println(s"i using to  $i")
    }

    for (i <- 1 until 5) {
      println(s"i using until  $i")
    }
    for (i <- 1.until(5)) {
      println(s"i using until  $i")
    }
    //iterate multiple values (nesting approach : first i to all j, second i to all j, ...)
    for (i <- 1.to(5); j <- 1.to(3)) {
      println(s"i using to  $i : j using to $j")
    }
    //iterating list
    val lst = List(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
    for (i <- lst) {
      println(s"i using lst $i")
    }

    //with filter
    for (i <- lst; if i < 6) {
      println(s"i using lst $i")
    }
    //for loop as expression
    val result = for { i <- lst; if i < 6 } yield {
      i * i
    }
    println(s"result = $result")
  }
}