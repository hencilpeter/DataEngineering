package eh.trade.process.svc.sceduler;

import eh.trade.process.svc.repository.TradeRepository;
import eh.trade.process.svc.service.KafkaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class MessageProcessor {
    @Autowired
    TradeRepository repository;

    @Autowired
    KafkaService kafkaService;

    public MessageProcessor(){
    }
    @Scheduled(fixedRate=500)
    public void checkRecords() {

        //consume and process the message
        kafkaService.processMessage();
    }

}
