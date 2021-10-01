

object MatchExpressions {
  def main(args:Array[String]){
    val age = 20;
    
    age match{
      case 20 => println(age);
      case 18 => println(age);
      case _=> println("default")
    }
    
    val name ="hencil"
    
    val result = name match{
      case "hencil" => name;
      case _ => "default"
    }
    println(name)
    println(result)
    
    val i = 6;
    i match{
      case 1|3|5|7|9 => println("odd number")
      case 2|4|6|8|10 => println("even number")
    }
    
  }
}