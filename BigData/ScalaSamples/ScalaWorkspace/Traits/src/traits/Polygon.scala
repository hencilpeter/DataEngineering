package traits

trait Shape{ // at least one method in trait is abstract
   def color: String;  
}

abstract class Polygon {
  def area: Double;
}

object Polygon{
    def main(args: Array[String]){
      var rect = new Rectangle(10,22);
      var triangle = new Triangle(10,22);
      printArea(rect)
       println(rect.color)
        printArea(triangle)
        println(triangle.color)
    }
    
    def printArea(p: Polygon) {
      println(p.area)
     
    }
}