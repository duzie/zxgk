package com.f.zxgk;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
@MapperScan("com.f.zxgk.mapper")
public class ZxgkApplication {

    public static void main(String[] args) {
        SpringApplication.run(ZxgkApplication.class, args);
    }

}
