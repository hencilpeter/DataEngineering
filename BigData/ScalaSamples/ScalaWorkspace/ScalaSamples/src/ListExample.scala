
object ListExample {
 val myList: List[Int] = List(1,2,3,4,5,6);
 val names: List[String] = List("Tom", "Max");
 def main(args:Array[String]) {
    println(0::myList) // prepend values 
    println(myList)
    println(names)
    println(1:: 5 :: 9 :: Nil) // :: --cons 
    print(names.tail)
    println(names.isEmpty)
    //uniform list 
    println(List.fill(5) (2)) // fill 5 elements of 2
    myList.foreach(println)  //loop 
    var sum : Int = 0;
    myList.foreach(sum+=_)
    println(f"sum $sum")
    for (name <- names) {
      println(name)
    }
  }
}