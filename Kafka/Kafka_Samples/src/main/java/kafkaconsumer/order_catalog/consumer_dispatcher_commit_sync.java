/*
 * Author : J Hencil Peter
 * Program : Dispatcher class with Asynchronous commit
 * Purpose : This program is being used by the Async consumer program.
 * */

package kafkaconsumer.order_catalog;

import kafkaconsumer.simple.time_consumption;
import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.common.TopicPartition;

import java.text.MessageFormat;

public class consumer_dispatcher_commit_sync implements Runnable {
    private final Consumer<String, String> threadConsumer;
    private final TopicPartition topicPartition;

    consumer_dispatcher_commit_sync(Consumer<String, String> _threadConsumer, TopicPartition _topicPartition) {
        threadConsumer = _threadConsumer;
        topicPartition = _topicPartition;
    }

    @Override
    public void run() {
        System.out.printf(MessageFormat.format("\nConsumer Thread Starting...Topic : {0} , Partition : {1}",
                topicPartition.topic(), topicPartition.partition()));
        long startTime = System.currentTimeMillis();
        long recordCount = 0;

        try {
            while (true) {
                ConsumerRecords<String, String> records = threadConsumer.poll(1000);
                for (ConsumerRecord<String, String> record : records) {
                    //System.out.printf("\ntopic name : %s, partition = %s, offset = %d, record key = %s, record value = %s",
                    //        record.topic(), record.partition(), record.offset(), record.key(), record.value());

                    recordCount++;

                    if (recordCount == 10000 || recordCount == 100000 || recordCount == 300000
                            || recordCount == 500000 || recordCount == 1000000 ){
                        time_consumption.printTimeDifferenceInSeconds(startTime, System.currentTimeMillis(), recordCount);
                    }

                }
                threadConsumer.commitSync();
            }

        } catch (Exception exception) {
            System.out.print("Exception : ex : " + exception);
        }
    }
}
