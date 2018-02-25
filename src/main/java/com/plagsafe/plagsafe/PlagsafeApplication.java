package com.plagsafe.plagsafe;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class PlagsafeApplication {

	public static void main(String[] args) {
		SpringApplication.run(PlagsafeApplication.class, args);
	}

	@RequestMapping("/hello")
	public String sayHello() {
		return "PlagSafe - Plagiarism Detector by Team-109";
	}
}
