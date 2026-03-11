package lk.ijse.sentura_test;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class SenturaTestApplication {

    public static void main(String[] args) {
        SpringApplication.run(SenturaTestApplication.class, args);
    }

}
