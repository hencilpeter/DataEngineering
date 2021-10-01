

object ScalaSets {
  //by default all sets are immutable
 val mySet : Set[Int] = Set(1,1,1,12,3,4,5,6);
 val mySet2 : Set[Int] = Set(12,13,14,15,16,17,18,19);
 val nameSet : Set[String] = Set("Tom", "Ben", "Adam");
 
 //mutable Sets 
 //var myMutableSet: scala.collection.immutable.Set
  def main (args:Array[String]){
    println(mySet)
    println(mySet(12)) //return boolean. true if it exist.
    println(nameSet("not exist"))
    println(nameSet.head); 
    println(nameSet.tail);//except head, all will be return.
    println(nameSet.isEmpty)
 
    println(mySet ++ mySet2) // ++ operator 
    println(mySet.++(mySet2)) //another approach.
    //intersection 
    println(mySet.&(mySet2))
    println(mySet.intersect(mySet2))
    println(mySet.min)
    //for loop
    for( x <- mySet){
      println(x)
    }
  }
}