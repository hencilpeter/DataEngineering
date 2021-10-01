

object IfElseExample {
  def main(args: Array[String]) {
    println("6. If Else Example")
    val x = 20;
    val y = 30
    var result = ""
    if (x == 20 && y == 30) {
      result = "x == 20 && y == 30"
    } else {
      result = "x != 20 OR y!= 30"
    }

    println(result)

    val result2 = if (x == 20) "x == 20" else "x != 20";
    println(result2)
    println(if (x == 20) "x == 20" else "x != 20")

  }

}