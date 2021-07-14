/*
*Author : J Hencil Peter
* Purpose : Kafka Storm Integration Sample
*  */
package kafkastorm;
//
//import org.apache.kafka.common.config.Config;
//import org.apache.kafka.common.errors.AuthorizationException;
//import org.apache.storm.StormSubmitter;
//import org.apache.storm.kafka.BrokerHosts;
//import org.apache.storm.kafka.StringScheme;
//import org.apache.storm.kafka.ZkHosts;
//import org.apache.storm.kafka.spout.SchemeAsMultiScheme;
//import org.apache.storm.kafka.trident.OpaqueTridentKafkaSpout;
//import org.apache.storm.kafka.trident.TridentKafkaConfig;
//import org.apache.storm.kafka.trident.TridentTopology;
//import org.apache.storm.kafka.trident.operation.BaseFunction;
//import org.apache.storm.kafka.trident.operation.TridentCollector;
//import org.apache.storm.kafka.trident.tuple.TridentTuple;
//import org.apache.storm.kafka.tuple.Fields;
//import org.apache.storm.kafka.tuple.Values;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//
//import java.util.stream.Stream;
//
//public class KafkaStormSpout {
//    private static final Logger log = LoggerFactory.getLogger(KafkaStormSpout.class);
//    public static void main(String[] args) throws  AuthorizationException, Exception
//    {
//        TridentTopology topology = new TridentTopology();
//
//        // Kafka Configs
//        BrokerHosts zk = new ZkHosts("localhost");
//        TridentKafkaConfig spoutConf = new TridentKafkaConfig(zk, "kafka-topic-one-partition");
//        spoutConf.scheme = new SchemeAsMultiScheme(new StringScheme());
//        OpaqueTridentKafkaSpout spout = new OpaqueTridentKafkaSpout(spoutConf);
//
//        // defining kafka stream
//        Stream kafkaStream =
//                topology.newStream("lines", spout).name("Kafka Spout")
//                        .each(spout.getOutputFields(), new PrintFunction(), new Fields("lines"));
//
//        StormSubmitter.submitTopologyWithProgressBar("kafka-spout", new Config(), topology.build());
//
//    }
//
//    static class PrintFunction extends BaseFunction
//    {
//        @Override
//        public void execute(TridentTuple tridentTuple, TridentCollector tridentCollector)
//        {
//            String msg = tridentTuple.getString(0);
//            log.info("Consumed Message from Kafka -------------------> {}",msg);
//            tridentCollector.emit(new Values(msg));
//        }
//    }
//}
