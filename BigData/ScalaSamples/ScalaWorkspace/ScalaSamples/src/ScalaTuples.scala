

object ScalaTuples {
  val myTuple = (1,2, "hello", true)
  //if we use new, we can use no.of elements after Tuple.
  //max - 1 to 22 elements. 
  val myTuple2 = new Tuple3(1,2, "Hai")
  def main(args:Array[String]) {
    println(myTuple)
    //upon declaring tuple, variables created with _ prefix and index.
    println(myTuple._1)
    println(myTuple._2)
    //iteration 
    myTuple.productIterator.foreach {
      x => println(x)
    }
    
    //tuple can be created using below approach as well
  //only two elements will be in a single tuple
  println(1 -> "Tom" -> true)
  
  val myTuple3 = new Tuple3(1,"Hai", (2,3));
  println(myTuple3._3._2)
  }
}