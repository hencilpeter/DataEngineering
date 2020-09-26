package eh.ticket.publisher.svc.scheduler;


import com.netflix.discovery.converters.Auto;
import eh.ticket.publisher.svc.service.KafkaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class TicketPublishScheduler {

    @Autowired
    KafkaService kafkaService;
    @Scheduled(fixedRate=500)
    public void checkRecords() {
        //consume and process the event
        kafkaService.procesEvent();
    }

}
