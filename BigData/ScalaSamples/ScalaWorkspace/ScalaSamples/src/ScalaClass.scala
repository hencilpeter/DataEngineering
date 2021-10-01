
//data type 
//construct must have var/val infront of member variables. 
//var     getter setter 
//val     getter  ----
//default --       -- 
class User ( var name:String, private var age: Int){
  def printName{
    println(name)
  }
}

object ScalaClass {
  
  def main(args : Array[String]) {
    //var user = new User;
    var user = new User("Tom", 25);
    println(user.name )
    user.name = "Ben"
    println(user.name )
    //if we use val, we can't overwrite the value.
    
    user.printName
  }
}