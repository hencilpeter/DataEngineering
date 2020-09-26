package eh.trade.listener.svc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class TradeListenerApplication {

	public static void main(String[] args) {
		SpringApplication.run(TradeListenerApplication.class, args);
	}

}
