//.......Getter? Setter?
//----------------------
//var     yes     yes 
//val     yes     no
//default no      no 
//primary construtor - must have different constrctor than auxiliary constructors. 
//auxiliary constructor - will call the previously defined constructor with required parameter. 
class User1(private var name: String, val age: Int) {
  def this(){
   this("Tom", 32) 
  }
  
  def this(name : String){
    this(name, 32);
  }
  def printName{
    println("Name " + name)
  }
}


object ClassAuxiliaryConstructor {
  
  def main(args: Array[String] ) {
    var user1 = new User1("Tom", 23);
    var user2 = new User1();
    var user3 = new User1("Tom");
    println(user3.printName)
  }
  
}