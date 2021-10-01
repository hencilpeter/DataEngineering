
//Scala - Reduce, fold or scan (left/right)
//reduceLeft, reduceRight, foldLeft
object ReduceFoldScan {
  val lst = List(1, 2, 3, 5, 7, 10, 13);
  val lst2 = List("A", "B", "C");
  def main(args: Array[String]) {
    //reduceLeft - takes associate binary operator function as parameter and will use it to collapse the elements.
    //i.e. apply the operator on first two operand, result with next operand and finally returns the result.
    println(lst.reduceLeft(_ + _))
    println(lst2.reduceLeft(_ + _))
    println(lst.reduceLeft((x, y) => { println(x + " , " + y); x + y }))
    println(lst.reduceLeft(_ - _))
    println(lst.reduceRight(_ - _))
    println(lst.reduceRight((x, y) => { println(x + " , " + y); x - y; }));

    //fold - similar as reduce but we can pass the initial argument in fold functions.
    println(lst.foldLeft(10)(_ + _)) //initial value 10 is added.
    println(lst2.foldRight("End")(_ + _))
    //scan - same as fold. it takes the starting value but scan will give the map of intermediate result.
    println(lst.scanLeft(10)(_ + _))
    println(lst2.scanRight("End")(_ + _))

  }

}