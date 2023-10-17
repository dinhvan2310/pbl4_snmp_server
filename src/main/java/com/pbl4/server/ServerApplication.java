package com.pbl4.server;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class })
@EnableMongoRepositories
public class ServerApplication implements CommandLineRunner {
	public static void main(String[] args) {
		SpringApplication.run(ServerApplication.class, args);
	}
	@Override
	public void run(String... args) throws Exception {
	}
}
