package ru.tashmetov;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import ru.tashmetov.loggingstarter.LoggingStarterAutoConfiguration;

@SpringBootApplication
public class LinkShortenerApp {

    public static void main(String[] args) {
        LoggingStarterAutoConfiguration.println("Hello from LoggingStarterAutoConfiguration");
        SpringApplication.run(LinkShortenerApp.class, args);
    }

}