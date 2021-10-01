

object Closure {
  var number = 10
  val add = (x: Int) => x + number
  def main(args: Array[String]) {
    number = 100
    println(add(20))
  }
}