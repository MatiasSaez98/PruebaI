package com.example.consumingrest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;
import com.google.gson.*;

@SpringBootApplication
public class ConsumingRestApplication {

	private static final Logger log = LoggerFactory.getLogger(ConsumingRestApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(ConsumingRestApplication.class, args);
	}

	@Bean
	public RestTemplate restTemplate(RestTemplateBuilder builder) {
		return builder.build();
	}

	@Bean
	public CommandLineRunner run(RestTemplate restTemplate) throws Exception {
		return args -> {
			//Gson gson = new Gson();
			//gson.fromJson("https://api.openweathermap.org/data/2.5/weather?q=London,uk&APPID=f5c936b7d3aff259ac99b795d48d6ac4", Country.class);
			String string = restTemplate.getForObject(
					"https://api.openweathermap.org/data/2.5/weather?q=London,uk&APPID=f5c936b7d3aff259ac99b795d48d6ac4", String.class);
			JsonObject jsonObject = new JsonParser().parse(string).getAsJsonObject();
			log.info(jsonObject.toString());
		};
	}
}
