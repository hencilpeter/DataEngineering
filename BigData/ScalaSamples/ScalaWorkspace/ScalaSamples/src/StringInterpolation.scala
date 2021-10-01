

object StringInterpolation {
  def main(args: Array[String]) {
    //6. string interpolation example
    val name = "mark"
    val age = 18
    //approach 1
    println(name + " is" + age + " year old")
    //approach 2 - using s before the string
    println(s"$name is $age years old")

    //approach 3 - using f before the string (type safe manner)
    //it will throw error if there is a type mismatch
    println(f"$name%s is $age%d years old")

    //print in raw form
    println(raw"Hello \n World")
    print(s"Hello\nWorld")
  }
}