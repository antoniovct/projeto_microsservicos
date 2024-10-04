package com.antonio_victor.projetos.distribuidora.cadastros;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class CadastrosApplication {

	public static void main(String[] args) {
		SpringApplication.run(CadastrosApplication.class, args);
	}

}
