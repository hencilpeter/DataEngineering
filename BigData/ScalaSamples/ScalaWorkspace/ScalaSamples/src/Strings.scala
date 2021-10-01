

object Strings {
  val str1: String = "Hello World" // same as java and it uses java lib.
  val str2:String = "max"
  val num1 = 75
  val num2 = 100.25
  def main(args:Array[String]){
    println(str1.length())
    println(str1.concat(" Max"))
    println(str1 + str2)
    val result = printf("(%d----%f --%s)", num1, num2, str1)
    print(result)
    println("(%d------%f-----%s)".format(num1, num2, str1))
    printf("(%d------%f-----%s)", num1, num2, str1)
  }
}