package com.fd.baseCrud;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.Instant;
import java.util.Date;

@SpringBootApplication
public class BaseCrudApplication {

	@Autowired
	EmployeeRespository employeeRespository;

	public static void main(String[] args) {
		SpringApplication.run(BaseCrudApplication.class, args);
	}

	@Bean
	CommandLineRunner initDatabase(){
		return args->{employeeRespository.save(new Employee("Sami",25, new Date("2001/01/26")));
		employeeRespository.save(new Employee("Ali",30,new Date("1980/05/20")));
		};
	}

}
