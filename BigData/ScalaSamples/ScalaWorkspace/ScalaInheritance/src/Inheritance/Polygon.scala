package AbstractClass

class Polygon {
  def area: Double = 0.0;
}

object Polygon{
    def main(args: Array[String]){
      var poly = new Polygon;
      var rect = new Rectangle(10,22);
      var triangle = new Triangle(10,22);
      printArea(poly)
      printArea(rect)
      printArea(triangle)
    }
    
    def printArea(p: Polygon) {
      println(p.area)
    }
}