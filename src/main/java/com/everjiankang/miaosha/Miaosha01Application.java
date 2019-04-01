package com.everjiankang.miaosha;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
//@MapperScan("com.everjiankang.miaosha.dao")
public class Miaosha01Application {

	public static void main(String[] args) {
		SpringApplication.run(Miaosha01Application.class, args);
	}

}
