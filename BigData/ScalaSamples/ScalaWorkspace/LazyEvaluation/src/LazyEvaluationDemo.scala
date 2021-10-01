

class strict{
  val e = {
    println("strict")
   9
  }
}

class LazyEval{
  lazy val l= {
    println("lazy")
   9
  }
}

object LazyEvaluationDemo {
  def main(args: Array[String]) {
    //1st case - lazy is not evaluated as its not used.
    val x = new strict
    val y = new LazyEval
    println("----------------------")
    //2nd case 
     println(x.e)
     println(y.l)
  }
}