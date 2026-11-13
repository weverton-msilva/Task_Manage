package com.dev.taskmanage;

import org.springframework.boot.SpringApplication;

public class TestTaskmanageApplication {

	public static void main(String[] args) {
		SpringApplication.from(TaskmanageApplication::main).with(TestcontainersConfiguration.class).run(args);
	}

}
