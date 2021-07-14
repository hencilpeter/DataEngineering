package kafkaproducer.timertask;

import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.codehaus.jackson.map.ObjectMapper;

import java.io.File;
import java.sql.Timestamp;
import java.util.Scanner;
import java.util.Timer;

public class DispatcherAsync implements Runnable{
    private final Producer<String, String> producer;
    private final String topicName;
    private final String fileName;

    DispatcherAsync(Producer<String, String> _producer, String _topicName,
                String _fileName) {
        producer = _producer;
        topicName = _topicName;
        fileName = _fileName;
    }

    @Override
    public void run() {
        System.out.println("Producer Starting...");
        File fileObject = new File(fileName);
        ObjectMapper objectMapper = new ObjectMapper();
        long currentLineNumber = 1;
        Timestamp timestamp;
        try (Scanner scanner = new Scanner(fileObject)) {
            timestamp = new Timestamp(System.currentTimeMillis());
            System.out.println("Line #: " + currentLineNumber + "FileName : " + fileName + " timestamp : " + timestamp);

            CustomTimerTask customTimerTask = new CustomTimerTask();
            Timer timer = new Timer(true);
            System.out.println("Timer Started...");
            timer.scheduleAtFixedRate(customTimerTask, 0, 2 * 1000);

            long startTimeInMilliSeconds = System.currentTimeMillis();

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();

                String[] arrString = line.split(",");
                //construct objet
                CatalogDetail catalogDetail = new CatalogDetail(arrString[0], arrString[1], arrString[2], arrString[3],
                        arrString[4], arrString[5], arrString[6], arrString[7], arrString[8], arrString[9],
                        arrString[10], arrString[11], arrString[12]);
                String jsonString = objectMapper.writeValueAsString(catalogDetail);

                //send the json message to the producer
                final ProducerRecord<String, String> producerRecord = new ProducerRecord<>(topicName, "key-temp", jsonString);
                customTimerTask.updatePublishedItems(currentLineNumber);
                producer.send(producerRecord, new ProducerCallback());

                if (currentLineNumber == 10000 || currentLineNumber == 100000
                        || currentLineNumber == 500000 || currentLineNumber == 1000000) {
                    timestamp = new Timestamp(System.currentTimeMillis());
                    System.out.println("Line #: " + currentLineNumber + "FileName : " + fileName + " timestamp : " + timestamp);

                }
                currentLineNumber++;
            }

            long timeSpentInMilliSeconds = System.currentTimeMillis() - startTimeInMilliSeconds;
            long seconds = timeSpentInMilliSeconds / 1000;
            System.out.println("Total Time spent : " + seconds + " seconds");
            System.out.println("Overall Transfer rate : " +  (double)currentLineNumber/(double)seconds + " records per second");

        } catch (Exception exception) {
            System.out.println("Exception in thread. exception : " + exception);
        }
    }
}
