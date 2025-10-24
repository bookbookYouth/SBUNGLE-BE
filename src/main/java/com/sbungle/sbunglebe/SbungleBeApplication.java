package com.sbungle.sbunglebe;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class SbungleBeApplication {

    public static void main(String[] args) {
        SpringApplication.run(SbungleBeApplication.class, args);
    }

}
