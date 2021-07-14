package kafkastream.statefultransformation;

import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.StreamsConfig;
import org.apache.kafka.streams.kstream.*;
import org.apache.kafka.streams.KafkaStreams;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Properties;

public class StatefulTransformation {
    public static void main(String[] args) {
        Properties properties = new Properties();
        properties.put(StreamsConfig.APPLICATION_ID_CONFIG, "Stateful-example");
        properties.put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092, localhost:9092");
        properties.put(StreamsConfig.DEFAULT_KEY_SERDE_CLASS_CONFIG, Serdes.String().getClass().getName());
        properties.put(StreamsConfig.DEFAULT_VALUE_SERDE_CLASS_CONFIG, Serdes.String().getClass().getName());
        properties.put(StreamsConfig.COMMIT_INTERVAL_MS_CONFIG, "1000");

        StreamsBuilder streamsBuilder = new StreamsBuilder();
        KStream<String, String> source =    streamsBuilder.stream("kafka-topic-invoice");
        KStream<String, Long> result = source.groupByKey().windowedBy(TimeWindows.of(6000).advanceBy(1000))
                .count().toStream((k,v) -> k.key() + "-> [" +
                        millisecondsToDateStr(k.window().start()) + "  to " +
                        millisecondsToDateStr(k.window().end()));
        result.to("kafka-topic-result");
        KafkaStreams kafkaStreams = new KafkaStreams(streamsBuilder.build(), properties);

//        KStreamBuilder builder = new KStreamBuilder();
//        KStream<String, String> source = builder.stream("kafka-topic-invoice");
//        KStream<String, Long> result = source
//                .groupByKey()
//                .count( TimeWindows.of(6000).advanceBy(1000))
//                //.count(TimeWindows.of(60000))
//                .toStream( (k,v) -> k.key() + "-> [" +
//                        millisecondsToDateStr(k.window().start()) + "  to " +
//                        millisecondsToDateStr(k.window().end()));
//
//        //writing result to result topic
        //result.to(Serdes.String(), Serdes.Long(), "kafka-topic-result");
        //KafkaStreams kafkaStreams = new KafkaStreams(builder, properties);


        kafkaStreams.cleanUp();
        kafkaStreams.start();
        Runtime.getRuntime().addShutdownHook(new Thread(kafkaStreams::close));


    }

    public static String millisecondsToDateStr(long milliseconds){
        LocalDateTime localDateTime =LocalDateTime.ofInstant(Instant.ofEpochMilli(milliseconds), ZoneId.systemDefault());
        return localDateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    }
}
