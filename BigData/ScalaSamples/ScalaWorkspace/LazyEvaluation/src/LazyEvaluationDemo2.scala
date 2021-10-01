



object LazyEvaluationDemo2 {

  def method1(n :Int) {
    println("Method1")
    println(n)
  }
  
  
  //arrow symbol - call by value 
  def method2(n : => Int) {
    println("Method2")
    println(n)
  }
  
  def main(args: Array[String]): Unit = {
    val add = (a: Int, b:Int) => {
        println("Add");
        a + b
    }
    
    method1(add(5,6))
    method2(add(5,6))
  }
}