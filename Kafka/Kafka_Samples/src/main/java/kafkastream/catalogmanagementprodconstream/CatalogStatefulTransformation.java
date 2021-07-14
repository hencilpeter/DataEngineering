package kafkastream.catalogmanagementprodconstream;

import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.KafkaStreams;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.StreamsConfig;
import org.apache.kafka.streams.kstream.KStream;
import org.apache.kafka.streams.kstream.TimeWindows;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Properties;

public class CatalogStatefulTransformation {
    public static void main(String[] args) {
        Properties properties = new Properties();
        properties.put(StreamsConfig.APPLICATION_ID_CONFIG, "Stateful-example");
        properties.put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092, localhost:9092");
        properties.put(StreamsConfig.DEFAULT_KEY_SERDE_CLASS_CONFIG, Serdes.String().getClass().getName());
        properties.put(StreamsConfig.DEFAULT_VALUE_SERDE_CLASS_CONFIG, Serdes.String().getClass().getName());
        properties.put(StreamsConfig.COMMIT_INTERVAL_MS_CONFIG, "5000");

        StreamsBuilder streamsBuilder = new StreamsBuilder();
        //KStream<String, String> source =    streamsBuilder.stream("kafka-topic-one-partition");
        KStream<String, String> source =    streamsBuilder.stream("kafka-topic-two-partitions");

        //KStream<String, Long> result = source.groupByKey().windowedBy(TimeWindows.of(6000).advanceBy(1000))
        KStream<String, Long> result = source.groupByKey().windowedBy(TimeWindows.of(5000)) //1 second window
                .count().toStream((k,v) -> k.key() + "-> [" +
                        millisecondsToDateStr(k.window().start()) + "  to " +
                        millisecondsToDateStr(k.window().end()));
        result.to("kafka-topic-result");
        //result.to("kafka-topic-result-two-partitions");
        KafkaStreams kafkaStreams = new KafkaStreams(streamsBuilder.build(), properties);

        kafkaStreams.cleanUp();
        kafkaStreams.start();
        Runtime.getRuntime().addShutdownHook(new Thread(kafkaStreams::close));


    }

    public static String millisecondsToDateStr(long milliseconds){
        LocalDateTime localDateTime =LocalDateTime.ofInstant(Instant.ofEpochMilli(milliseconds), ZoneId.systemDefault());
        return localDateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    }
}
