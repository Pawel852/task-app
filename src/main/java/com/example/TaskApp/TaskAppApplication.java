package com.example.TaskApp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TaskAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(TaskAppApplication.class, args);
		System.out.println("Deleting useless files");
	}

}
