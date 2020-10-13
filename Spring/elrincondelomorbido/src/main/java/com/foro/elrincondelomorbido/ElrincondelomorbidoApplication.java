package com.foro.elrincondelomorbido;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;

@Configuration
	@PropertySources({
		@PropertySource("classpath:h2.properties")
	})
@SpringBootApplication
public class ElrincondelomorbidoApplication {	
	public static void main(String[] args) {
		SpringApplication.run(ElrincondelomorbidoApplication.class, args);
	}

}
