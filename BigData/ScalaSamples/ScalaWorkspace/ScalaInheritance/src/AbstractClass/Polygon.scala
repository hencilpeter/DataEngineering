package Inheritance

abstract class Polygon {
  def area: Double;
}

object Polygon{
    def main(args: Array[String]){
      var rect = new Rectangle(10,22);
      var triangle = new Triangle(10,22);
      printArea(rect)
      printArea(triangle)
    }
    
    def printArea(p: Polygon) {
      println(p.area)
    }
}