package com.project.FirstJavaApplication;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMethod;

@SpringBootApplication
@RestController
public class FirstJavaApplication {

	public static void main(String[] args) {
		SpringApplication.run(FirstJavaApplication.class, args);
	}
	@RequestMapping(value = "/hello", method = RequestMethod.GET)
	public String hello(@RequestParam(value = "name", defaultValue = "World") String name) {
		// return String.format("Hello %s %s!", name, constant.name);
		return String.format("Hello %s", name);
	}
}
