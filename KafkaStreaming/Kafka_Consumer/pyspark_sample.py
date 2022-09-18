from pyspark.sql import SparkSession

sparkSession = SparkSession.builder.master("local[1]").appName("SparkApp").getOrCreate()

# Create data
data = [('First', 1), ('Second', 2), ('Third', 3), ('Fourth', 4), ('Fifth', 5)]
#data = [('Six', 6), ('Seven', 7), ('Eight', 8), ('Nine', 9), ('Ten', 10)]
df = sparkSession.createDataFrame(data)

# Write into HDFS
#df.coalesce(1).write.mode('append').option('header','true').csv('hdfs://localhost:9000/data/')
#df.coalesce(1).write.option("header", "false").mode('append').csv('hdfs://localhost:9000/data/')
df.coalesce(1).write.format("csv").option("header", "false").mode('append').save('hdfs://localhost:9000/data/')

#print(df.rdd.getNumPartitions())