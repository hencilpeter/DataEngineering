/*
* Author : J Hencil Peter
* Purpose : Kafka - Spark Integration
* */
package kafkaspark.stream;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.streaming.Durations;
import org.apache.spark.streaming.api.java.JavaDStream;
import org.apache.spark.streaming.api.java.JavaInputDStream;
import org.apache.spark.streaming.api.java.JavaStreamingContext;
import org.apache.spark.streaming.kafka010.ConsumerStrategies;
import org.apache.spark.streaming.kafka010.KafkaUtils;
import org.apache.spark.streaming.kafka010.LocationStrategies;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class streamapplication {
    public static void main(String[] args) throws InterruptedException {

        //Used the below configuration for testing code in IDE.
        SparkConf conf = new SparkConf()
                .setAppName("kafka-spark")
                .setMaster("local");

        //SparkConf conf = new SparkConf().setAppName("kafka-spark");

        JavaSparkContext sc = new JavaSparkContext(conf);
        JavaStreamingContext ssc = new JavaStreamingContext(sc, Durations.seconds(100));

        Map<String, Object> kafkaParams = new HashMap<>();
        kafkaParams.put("bootstrap.servers", "localhost:9092");
        //kafkaParams.put("auto.offset.reset", "latest");
        kafkaParams.put("group.id", "kafka_spark_grp");
        kafkaParams.put("key.deserializer","org.apache.kafka.common.serialization.StringDeserializer");
        kafkaParams.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");

        Set<String> topics = Collections.singleton("kafka-topic-partition-one");


//        JavaInputDStream<MessageAndMetadata<String, String>> dStream = buildInputDStream(scc);
//        JavaPairDStream<String, byte[]> pairDStream = dStream.mapToPair(km -> new Tuple2<>(km.key(), km.message()));
//
//        pairDStream.foreachRDD(new ProcessStreamingData<>(config)); // process data
//        dStream.foreachRDD(new UpdateOffsetsFn<>(config.getKafkaGroupId(), config.getZkOffsetManager()));
//        ssc.start();

        JavaInputDStream<ConsumerRecord<String, String>> directKafkaStream = KafkaUtils.createDirectStream(ssc, LocationStrategies.PreferConsistent(),
                        ConsumerStrategies.Subscribe(topics, kafkaParams));

        JavaDStream<String> products =  directKafkaStream.map( (km -> km.value()));
        products.print();

        ssc.start();
        ssc.awaitTermination();
    }
}
