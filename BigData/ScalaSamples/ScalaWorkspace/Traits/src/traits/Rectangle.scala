package traits

class Rectangle(var width : Double, var height:Double) extends Polygon with Shape{
  override def area: Double = width * height;
  
  //we can skip override for trait.
  def color:String = "Rect Colour"
}