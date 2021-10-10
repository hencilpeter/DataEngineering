
import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.types.{StructField, StructType, StringType, LongType}
import org.apache.spark.sql.types.Metadata
import org.apache.spark.sql.functions.{col, column}
import org.apache.spark.sql.functions.expr

import org.apache.spark.sql.Row
import org.apache.spark.sql.functions.lit

import org.apache.spark.sql.functions.{desc, asc}

object MainClass extends App {
  println("Spark Mainclass Invoked- basic structured operations!!!")
   val sc = SparkSession.builder().master("local[1]")
          .appName("sparkHelloWorld")
          .getOrCreate()
     
          
    println("Spark Context created!!!!")
    
    //1. load data from json file from disk - print schema 
    val df = sc.read.format("json").load("file:///home/osboxes/Hencil/Projects/SparkSamples/data/2015-summary.json")
    df.printSchema()
    
    //approach 2
    println("Schema " + df.schema)
    
    //2. manual schema
    val manualSchema = StructType(Array(StructField("DEST_COUNTRY_NAME", StringType,true), 
        StructField("ORIGIN_COUNTRY_NAME",StringType,true),
        StructField("count",LongType,false, Metadata.fromJson("{\"hello\":\"World\"}"))
        ))
    val df2 = sc.read.format("json").schema(manualSchema)
    .load("file:///home/osboxes/Hencil/Projects/SparkSamples/data/2015-summary.json")    
    
    println("Manual Schema - Example \n " + df2.schema)   
    
    //3. column example
    println(col("testcolumn1"))
    println(column("testcolumn2")) ///?
    println(df2.col("count"))
   
    println(expr("(somecol + 5) * 200"))    //?
    //dataframe columns
    println("Columns : "+ df2.columns)
    println("Columns count: "+ df2.columns.length)
    println("Columns 1: "+ df2.columns(0))
    println("Columns 2: "+ df2.columns(1))
    println("Columns 3: "+ df2.columns(2))
    
    //4. records and rows
    //1st row
    println("First Row : " + df2.first())
    
    //create rows
    val myRow = Row("Hello", null, 1, false)
    //access column
    myRow(0)
    println(myRow(0).asInstanceOf[String])
    myRow.getString(0)
    myRow.getInt(2)
    
    //5. creating dataframe 
    //approach 1 : above - first one 
    //approach 2 : using manual schema, create new row and create DF 
          
    val myManualSchema2 = new StructType(Array(StructField("DEST_COUNTRY_NAME", StringType,true), 
        StructField("ORIGIN_COUNTRY_NAME",StringType,true),
        StructField("count",LongType,false))) 
      
      
    val myRows = Seq(Row("Tom", "Green Valley", 1L))
    val myRDD =  sc.sparkContext.parallelize(myRows)
    val myDF3 = sc.createDataFrame(myRDD, myManualSchema2)
    println("New DataFrame  : " + myDF3.show()) 
        
//6. select and selectexpr
    println("Select")
   df.select("DEST_COUNTRY_NAME", "ORIGIN_COUNTRY_NAME").show(2)
   df.select(col("DEST_COUNTRY_NAME")).show(2)
   df.select(expr("DEST_COUNTRY_NAME")).show(2)
   println("SelectExpr")
   df.selectExpr("*", "DEST_COUNTRY_NAME as DestinationCountryName", "ORIGIN_COUNTRY_NAME", "DEST_COUNTRY_NAME == ORIGIN_COUNTRY_NAME").show(2)
        
   //7. converting to spark types (literals)
   df.select(expr("*"), lit(1).as("One")).show(2)
   
   //8.Adding columns
   df.withColumn("NumberOne", lit(1)).show(2)
   df.withColumn("IsWithInCountry", expr("DEST_COUNTRY_NAME == ORIGIN_COUNTRY_NAME")).show(2)
   
   //9.Renaming Columns
   df.withColumnRenamed("DEST_COUNTRY_NAME", "DESTINATION_COUNTRY").show(3)
   
   //10. Reserved Characters and keywords 
   val dfWithLongColumnName = df.withColumn("This Long Column-Name origin country", expr("ORIGIN_COUNTRY_NAME"))
   println("Reverved Characters - Long column name")
   dfWithLongColumnName.show(2)
   
   //use tick character
   dfWithLongColumnName.selectExpr("`This Long Column-Name origin country`", "`This Long Column-Name origin country` as `new col`").show(2)
   //11. remove columns
   println("Remove columns")
   println(dfWithLongColumnName.columns.mkString("|"))
   println(dfWithLongColumnName.drop("DEST_COUNTRY_NAME").columns.mkString("|"))
   
   //12. changing column types 
   println("Change Column Types")
   println(df.schema)
   println(df.withColumn("count2", col("count").cast("long")).schema)
   
   //13. filtering rows 
   //using where and filter
   df.where("count < 2").show(2)
   df.where(col("count") < 2).where(col("ORIGIN_COUNTRY_NAME") =!= "Croatia").show(2)
   
   df.filter(col("count") < 2).show(2)
   
   //14.unique rows 
    println(df.select("ORIGIN_COUNTRY_NAME", "DEST_COUNTRY_NAME").distinct().count())
    
   //15.random samples
    val seed = 5
    val withReplacement = false
    val fraction = 0.5
    println(" random sample : " + df.sample(withReplacement, fraction, seed).count()) 
   //16.random splits
  val dataFrames = df.randomSplit(Array(0.25, 0.75), seed)
  println("split result count comparison : " + (dataFrames(0).count() > dataFrames(1).count())) 
   //17.concatenating and appending
  val schema = df.schema
  val newRows = Seq(
      Row("New Country", "Other Country", 5L),
      Row("New Country 2", "Other Country", 1L))
      
  val parallelizedRows = sc.sparkContext.parallelize(newRows)
  val newDF = sc.createDataFrame(parallelizedRows,schema)
  df.union(newDF)
  .where("count = 1")
  .where(col("ORIGIN_COUNTRY_NAME") =!= "United States")
   .show(2)
   
   //18.sorting rows
   //sort or orderby
   df.sort("count").show(2)
   df.orderBy( col("count"), col("DEST_COUNTRY_NAME")).show(2)
   
   //desc /asc 
   df.orderBy(desc("count"), asc("DEST_COUNTRY_NAME")).show(2)
   df.orderBy( expr("DEST_COUNTRY_NAME desc")).show(2)
   //19.limit
   df.limit(5).show()
   
   //20. Repartition and coalesce 
   println("Repartition and coalesce")
   println("getNumPartitions " +  df.rdd.getNumPartitions)
   df.repartition(col("DEST_COUNTRY_NAME"))
   df.repartition(5, col("DEST_COUNTRY_NAME"))
   println("getNumPartitions " +  df.rdd.getNumPartitions)
   df.limit(2).show()
   
   df.repartition(5, col("DEST_COUNTRY_NAME")).coalesce(2)  //will not require full shuffle
   
   //21.collecting rows to the driver 
   println("Collecting rows")
   println("limit ")
   val collectDF = df.limit(10)
   println("take 5 ")
   collectDF.take(5).foreach(println) //take returns an array
   println("show all ")
   collectDF.show()
   println("show but truncate false ")
   collectDF.show(5, false) 
   println("collect ")
   collectDF.collect().foreach(println) //collect returns array
   
   
   
}