import org.apache.spark.sql.SparkSession

object MainClass extends App {
  println("Spark Mainclass Invoked")
   val sc = SparkSession.builder().master("local[1]")
          .appName("sparkHelloWorld")
          .getOrCreate()
           
    println("Spark Context created....")
}