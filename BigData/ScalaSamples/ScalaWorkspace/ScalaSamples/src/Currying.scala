

object Currying {
  def add(x:Int, y: Int) = x+ y
  def add2(x:Int) = (y:Int) => x+ y;
  def add3(x:Int)(y:Int) = x+ y;
  def main(args:Array[String]){
      println(add(5,10))
      println(add2(5)(10))
      
      //second approach of calling 
      val sum40 = add2(40)
      println(sum40(20))
      //third approach - new signature 
      println(add3(100)(200));
      //fourth appraoch 
      //val Sum50 = add3(50) // now error. so use partial argument
      val sum50 = add3(50)_;
      println(sum50(400))
  }
}