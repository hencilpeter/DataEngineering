package eh.ticket.publisher.svc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class TicketPublisherApplication {

	public static void main(String[] args) {
		SpringApplication.run(TicketPublisherApplication.class, args);
	}

}
