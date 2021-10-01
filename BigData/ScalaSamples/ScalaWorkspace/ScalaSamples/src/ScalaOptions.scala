

object ScalaOptions {
  val lst = List(1,2,3)
  val map = Map(1-> "Tom", 2-> "Ben", 3-> "Jeff")
  def main(args: Array[String]) {
    println(lst.find( _ > 7))
    println(lst.find( _ > 1)) //if exist first instance
    //extract the value 
    println(lst.find( _ > 1).get)
    println(lst.find( _ > 7).getOrElse("No name found"))
    //Option declaration 
    val opt : Option[Int] =None;
    println(opt.isEmpty)
    val opt2 : Option[Int] =Some(5);
    println(opt2.isEmpty)
    
  }
}