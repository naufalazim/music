package com.project.musicappbackend;

import com.project.musicappbackend.services.StorageService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class MusicAppBackendApplication {


	public static void main(String[] args) {

		//Application Context (Interface)
	 	ApplicationContext context = SpringApplication.run(MusicAppBackendApplication.class, args);


		StorageService storageService = context.getBean(StorageService.class);

		//Call class function getSongFileNames:
//		storageService.getSongFileNames();
		System.out.println(storageService.getSongFileNames());

	}

}
