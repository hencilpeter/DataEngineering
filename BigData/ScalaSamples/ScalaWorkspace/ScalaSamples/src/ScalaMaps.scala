

object ScalaMaps {
  //default Maps are mutable 
  val myMap: Map[Int, String] = Map(1-> "Pete", 
      2-> "Tom", 3-> "Ben", 4-> "George")
 val myMap2: Map[Int, String] = Map(5-> "Joe")     
  def main(args: Array[String]){
    println(myMap)
    for(x <- myMap){
      println(x._1)
      println(x._2)
    }
    println(myMap.keys)
    println(myMap.values)
    println(myMap.isEmpty)
    println(myMap(4))
    //println(myMap(9)) //exception as 9 key does not exist
    //iterate the map 
    myMap.keys.foreach{
      key=> 
        println("key "+ key)
        println("value "+ myMap(key))
    }
    //key present in the map 
    println(myMap.contains(5))
    println(myMap ++ myMap2)
    
  }
}