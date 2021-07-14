package kafkaconsumer.simple;

import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.common.TopicPartition;

import java.text.MessageFormat;

public class consumer_dispatcher_commit_sync implements Runnable{
    private final Consumer<String, String> threadConsumer;
    private final TopicPartition topicPartition;

    consumer_dispatcher_commit_sync(Consumer<String, String> _threadConsumer, TopicPartition _topicPartition ) {
        threadConsumer = _threadConsumer;
        topicPartition = _topicPartition;
    }

    @Override
    public void run() {
        System.out.printf(MessageFormat.format("\nConsumer Thread Starting...Topic : {0} , Partition : {1}",
                topicPartition.topic(), topicPartition.partition()));
        long startTime = System.currentTimeMillis();
        long recordCount = 0;

        while(true){
            ConsumerRecords<String, String> records = threadConsumer.poll(1000);
            for(ConsumerRecord<String, String> record: records){
                System.out.printf("\ntopic name : %s, partition = %s, offset = %d, record key = %s, record value = %s",
                        record.topic(), record.partition(), record.offset(), record.key(), record.value());

                recordCount++;

                time_consumption.printTimeDifferenceInSeconds(startTime, System.currentTimeMillis(), recordCount);
            }
            threadConsumer.commitSync();
        }

    }
}
