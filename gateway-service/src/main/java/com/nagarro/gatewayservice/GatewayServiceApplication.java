package com.nagarro.gatewayservice;

/**
 *  @author rishabhsinghla
 *  Gateway service for starting point of the assignment
 */

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.http.codec.ClientCodecConfigurer;
import org.springframework.http.codec.json.Jackson2JsonDecoder;
import org.springframework.web.reactive.function.client.ExchangeStrategies;
import org.springframework.web.reactive.function.client.WebClient;

@SpringBootApplication
public class GatewayServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(GatewayServiceApplication.class, args);
	}

	@Bean
	public WebClient webClient() {
		return WebClient.builder()
				.exchangeStrategies(ExchangeStrategies.builder().codecs(this::configureCodecs).build()).build();
	}

	private void configureCodecs(ClientCodecConfigurer clientCodecConfigurer) {
		clientCodecConfigurer.customCodecs().registerWithDefaultConfig(new Jackson2JsonDecoder());
	}

}
