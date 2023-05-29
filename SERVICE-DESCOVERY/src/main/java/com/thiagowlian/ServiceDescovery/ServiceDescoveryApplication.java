package com.thiagowlian.ServiceDescovery;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class ServiceDescoveryApplication {

	public static void main(String[] args) {
		SpringApplication.run(ServiceDescoveryApplication.class, args);
	}

}
