package com.abidi;

import org.springframework.boot.autoconfigure.SpringBootApplication;

import static org.springframework.boot.SpringApplication.run;

@SpringBootApplication(scanBasePackages = "com.abidi")
public class MyprojectApplication {

	public static void main(String[] args) {
		run(MyprojectApplication.class, args);
	}
}
