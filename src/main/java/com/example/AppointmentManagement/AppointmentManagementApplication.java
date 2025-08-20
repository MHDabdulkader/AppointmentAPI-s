package com.example.AppointmentManagement;

import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Objects;

@SpringBootApplication
public class AppointmentManagementApplication {

	public static void main(String[] args) {
        Dotenv env = Dotenv.load();
        System.setProperty("DB_URL", Objects.requireNonNull(env.get("DB_URL")));
        System.setProperty("DB_USERNAME", Objects.requireNonNull(env.get("DB_USERNAME")));
        System.setProperty("DB_PASSWORD", Objects.requireNonNull(env.get("DB_PASSWORD")));
        SpringApplication.run(AppointmentManagementApplication.class, args);

        System.out.println("=========== DB_URL ========== \n"+ System.getProperty("DB_URL"));
	}

}
