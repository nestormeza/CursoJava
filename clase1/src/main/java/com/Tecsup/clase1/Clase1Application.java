package com.Tecsup.clase1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class Clase1Application {

	public static void main(String[] args) {
		SpringApplication.run(Clase1Application.class, args);
	}

}
