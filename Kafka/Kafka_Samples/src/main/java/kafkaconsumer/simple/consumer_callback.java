package kafkaconsumer.simple;

import org.apache.kafka.clients.consumer.OffsetAndMetadata;
import org.apache.kafka.clients.consumer.OffsetCommitCallback;
import org.apache.kafka.common.TopicPartition;

import java.util.Map;

public class consumer_callback implements OffsetCommitCallback {
    private  void printMetaData(final Map< TopicPartition, OffsetAndMetadata > metadataMap){
        //print the data
    }
    @Override
    public void onComplete(Map< TopicPartition, OffsetAndMetadata > metadataMap, Exception exception){
        if (exception!= null) {
            System.out.println("Asynchronous producer failed  with exception.");
            System.out.println("Exception : " + exception);
        }
        else{
            //System.out.print("Asynchronous producer succeeded!!!");
            printMetaData(metadataMap);
        }
    }

}
