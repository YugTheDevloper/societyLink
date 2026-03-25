package org.yug.societylink;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class SocietyLinkApplication {

    public static void main(String[] args) {
        SpringApplication.run(SocietyLinkApplication.class, args);
    }

    @Bean
    public org.modelmapper.ModelMapper modelMapper() {
        return new org.modelmapper.ModelMapper();
    }
}
