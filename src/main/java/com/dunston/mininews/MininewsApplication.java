package com.dunston.mininews;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.dunston.mininews.mapper")
public class MininewsApplication {

    public static void main(String[] args) {
        SpringApplication.run(MininewsApplication.class, args);
    }

}
