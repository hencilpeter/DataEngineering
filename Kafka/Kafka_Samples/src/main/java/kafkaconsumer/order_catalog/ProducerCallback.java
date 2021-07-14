/*
 * Author : J Hencil Peter
 * Program : Producer Callback, being used by producer application.
 * Purpose : This program is created to test the consumer samples.
 * */

package kafkaconsumer.order_catalog;

import org.apache.kafka.clients.producer.Callback;
import org.apache.kafka.clients.producer.RecordMetadata;

public class ProducerCallback implements Callback {
    private  void printMetaData(final RecordMetadata metadata){
        //System.out.println("Message written Topic : "+ metadata.topic());
        //System.out.println("Partition : "+ metadata.partition());
        //System.out.println("Offset : "+ metadata.offset());
    }
    @Override
    public void onCompletion(RecordMetadata recordMetadata, Exception exception){
        if (exception!= null) {
            System.out.println("Asynchronous producer failed  with exception.");
            System.out.println("Exception : " + exception);
        }
        else{
            //System.out.print("Asynchronous producer succeeded!!!");
            printMetaData(recordMetadata);
        }
    }
}