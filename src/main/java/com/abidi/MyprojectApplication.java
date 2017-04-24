package com.abidi;

import com.abidi.config.AppConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

import static org.springframework.boot.SpringApplication.run;

@SpringBootApplication(scanBasePackages = "com.abidi")
@Import(AppConfiguration.class)
public class MyprojectApplication {


	public static void main(String[] args) {
		run(MyprojectApplication.class, args);
	}
}
