package com.java.week4;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class StartupListener implements CommandLineRunner {

	@Value("${app.welcomeMessage}")
	private String welcomeMessage;

	@Override
	public void run(String... args) throws Exception {
		System.out.println(welcomeMessage);
	}
}
