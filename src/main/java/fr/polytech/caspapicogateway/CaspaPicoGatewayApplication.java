package fr.polytech.caspapicogateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

@SpringBootApplication
@EnableZuulProxy
public class CaspaPicoGatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(CaspaPicoGatewayApplication.class, args);
	}

}
