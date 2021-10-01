import Array._

object Arrays {
  
  //default values for array
  //string -> null
  //Boolean -> false 
  //Int = 0;
  //Double = 0.0
  //approach 1
  val myArray1: Array[Int] = new Array[Int](4)
  
  //approach 2
  val myArray2 =  new Array[Int](5)
  
  //approach 3 
  val myArray3 = Array(1,2,3,4,5,6,7);
  def main(args:Array[String]){
    myArray1(0) = 20;
    myArray1(1) = 30;
    myArray1(2) = 40;
    myArray1(3) = 50;
    println(myArray1)
    
    for(x <- myArray1){
      println(x)
    } 
    
    for(i <- 0 to (myArray1.length -1 )) {
      println(myArray1(i))
    }    
    // concatination  
    println("Concatination Result")
    val result=concat(myArray1, myArray2)
    for (x <- result){
      println(x)
    }
      
  }
}