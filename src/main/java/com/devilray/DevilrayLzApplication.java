package com.devilray;

import com.devilray.webutils.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;




@SpringBootApplication
@EnableScheduling
public class DevilrayLzApplication {


	public static void main(String[] args) {

		SpringApplication.run(DevilrayLzApplication.class, args);
		


	}



}
