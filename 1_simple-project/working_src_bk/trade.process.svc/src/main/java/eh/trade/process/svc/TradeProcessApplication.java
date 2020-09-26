package eh.trade.process.svc;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.scheduling.annotation.EnableScheduling;


@SpringBootApplication
@EnableDiscoveryClient
@EnableScheduling
public class TradeProcessApplication {
	private static final Logger logger = LogManager.getLogger(TradeProcessApplication.class);
	public static void main(String[] args) {
		SpringApplication.run(TradeProcessApplication.class, args);
	}

}
