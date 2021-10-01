
import java.util.Date

object PartiallyAppliedFunctions {

  def log(date: Date, message: String) = {
    println(date + "  " + message)
  }

  def main(args: Array[String]) {
    val sum = (a: Int, b: Int, c: Int) => a + b + c
    //partially applied function
    val f = sum(10, 20, _: Int) //_ is wildcard. it will be fed later.
    println(f(100))

    //real world example.
    val date = new Date;
    val newLog = log(date, _: String) //partially applied function
    newLog("The messae 1")
    newLog("The messae 2")
  }
}