package kafkastream.statelesstransformation;


//Hencil : TODO: this code is not working as expected.
//Hencil : Need to fix
import com.google.gson.Gson;
import kafkastream.invoiceproducer.Invoice;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.KafkaStreams;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.StreamsConfig;
import org.apache.kafka.streams.kstream.KStream;
import org.codehaus.jackson.map.ObjectMapper;

import java.io.IOException;
import java.util.Properties;

public class StatelessTransformation {
    public static void main(String[] args)  {

        //Gson gson = new Gson();
        Properties properties = new Properties();
        properties.put(StreamsConfig.APPLICATION_ID_CONFIG, "Stateless-example");
        properties.put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092, localhost:9092");
        properties.put(StreamsConfig.DEFAULT_KEY_SERDE_CLASS_CONFIG, Serdes.String().getClass().getName());
        properties.put(StreamsConfig.DEFAULT_VALUE_SERDE_CLASS_CONFIG, Serdes.String().getClass().getName());
        properties.put(StreamsConfig.COMMIT_INTERVAL_MS_CONFIG, "1000");

        ObjectMapper objectMapper = new ObjectMapper();
        StreamsBuilder streamsBuilder = new StreamsBuilder();
        KStream<String, String> source =    streamsBuilder.stream("kafka-topic-invoice");
        //KStream<String, Invoice> t = source.mapValues(str -> gson.fromJson(str,Invoice.class));
        KStream<String, Invoice> t = source.mapValues(str -> {
            Invoice invoice = null;
            try {
                invoice = objectMapper.readValue(str,Invoice.class);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return invoice;});

        t.mapValues(invoice -> {if ( invoice!= null && invoice.getInvoiceAmount() > 1000)
        {
        invoice.setDiscount(5);
        }
            String jsonInvoice= null;
            try {
                jsonInvoice = objectMapper.writeValueAsString(invoice);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return jsonInvoice;
        })
                .to("kafka-topic-result");


        KafkaStreams streams = new KafkaStreams(streamsBuilder.build(),properties);
        streams.cleanUp();
        streams.start();
        Runtime.getRuntime().addShutdownHook(new Thread(streams::close));

    }
}
