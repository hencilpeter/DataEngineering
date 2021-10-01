

object WhileLoopAndDoWhile {
  def main(args: Array[String]) {
    var x = 0;
    while (x < 10) {
      println("x = " + x)
      x += 1; //incremental operator is not allowed (i.e ++ or --)
    }

    var y = 0;
    do {
      println(s"y == $y")
      y += 1;
    } while (y < 10);
  }
}