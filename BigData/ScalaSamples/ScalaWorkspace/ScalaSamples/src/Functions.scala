

object Functions {
  object Math {

    def add(x: Int, y: Int): Int = {
      return x + y
    }

    def subtract(x: Int, y: Int): Int = {
      x - y
    }

    def multiply(x: Int, y: Int): Int = x * y

    def divide(x: Int, y: Int) = x / y

    def square(x:Int) = x * x
    
  }

  def main(args: Array[String]) {
    
    println(Math.add(10,20))
    println(Math.subtract(10, 23))
    println(Math.multiply(10, 23))
    println(Math.divide(10, 23))
    println(Math square 5)  // if single argument, no need to use ()
  }
}