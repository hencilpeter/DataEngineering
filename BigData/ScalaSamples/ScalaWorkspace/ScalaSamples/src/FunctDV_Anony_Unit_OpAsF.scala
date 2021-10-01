

object FunctDefaultValue {
  object Math{
    def add(x:Int, y:Int) : Int ={
      return x+y
    }
    def +(x:Int, y:Int) : Int ={ //not operator overloading.
      return x+y                //but function     
    }
  }
  def print(x:Int, y:Int): Unit = { //Unit == not return value.
      println(x+y);
    }
  
  def defaltAdd(x: Int, y: Int=20) : Int ={
    return x + y
  }
  def main(args:Array[String]){
    print(10,20) //Unit - no return value
    println(Math.+(10, 25));//operator as function 
    println(defaltAdd(3)) //default argument
    var add = (x: Int, y: Int) => x+y //anonymous function 
    println(add(10,23))
    
  }
}