package com.service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

@SpringBootApplication
@RestController
public class ServiceApplication {
	protected final Logger log = LoggerFactory.getLogger(this.getClass());

	@RequestMapping(value = "/wordladder")
	public String ladder(String a, String b) throws IOException
	{
		log.debug("Creating wordladder between word " + a + " and " + b + "\n");
		Wordladder instance = new Wordladder();
		return instance.runLadder(a, b);
	}

	public static void main(String[] args) {
		SpringApplication.run(ServiceApplication.class, args);
	}
}
