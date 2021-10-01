

object HigherOrderFunction {
  def math(x: Double, y: Double, fun: (Double, Double)=> Double): Double = fun(x,y);
  
  def mathNestedFunction(x:Double, y:Double, z:Double, fun: (Double, Double)=> Double): Double= fun(fun(x,y),z);
  
  
  def main(args:Array[String]){
    //first approach 
    val result1 = math(50,20, (x,y)=> x+y)
    print(result1)
    val result2 = math(50,20, (x,y)=> x*y)
    print(result2)
    val result3 = math(50, 20, (x,y) => x max y);
    println(result3)
    
    //second approach 
    val result4 = mathNestedFunction(10, 20, 30, (x,y) => x *y)
    println(result4)
    
    // using "_". its whildcard. 
    val result5 = mathNestedFunction(10, 20, 30,  _*_)
    println(result4)
    
  }
}