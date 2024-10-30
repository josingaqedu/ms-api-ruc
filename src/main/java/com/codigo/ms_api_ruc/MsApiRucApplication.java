package com.codigo.ms_api_ruc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class MsApiRucApplication {

	public static void main(String[] args) {
		SpringApplication.run(MsApiRucApplication.class, args);
	}

}
