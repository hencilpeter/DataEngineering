//scala - map and filter

object ScalaMapFlatMapFilter {
  
  val lst = List(1,2,3,5,7,10,13);
  val myMap = Map(1-> "Tom", 2-> "Ben", 3-> "Jeff");
  
  def main(args: Array[String]){
    // map - iterate over collection (array, list, etc) 
    // and apply a function on each element 
    println(lst.map( x => x % 2))
    println(lst.map( x => "Hi" *x ))
    println(myMap.map(x => "Hi" + x))
    println("hello".map(x=> x.toUpper))
    //flatten - combine the list of list or flattern the contents of the list
    //below list of list
    println(List(List(1,2,3), List(4,5,6)).flatten)
    //FlatMap - map the collection and flatten it 
    println(lst.flatMap(x => List(x, x+1)))
    //filter - 
    println(lst.filter(x => x%2 ==0))
    println(lst.filter(x => x%2 !=0))
  }
}