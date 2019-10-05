package com.lucasloose.appfooturestars;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.lucasloose.appfooturestars.services.S3Service;

@SpringBootApplication
public class AppFootureStarsApplication implements CommandLineRunner {

	@Autowired
	private S3Service s3Service;
	

	public static void main(String[] args) {
		SpringApplication.run(AppFootureStarsApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		s3Service.uploadFile("C:\\Temp\\imgs\\homer.png");
	}

}
