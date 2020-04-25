package com.smarthealth.diningroom;

import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@Slf4j
@SpringBootApplication
@MapperScan("com.smarthealth.diningroom.mapper")
public class SmartHealthApplication {

    public static void main(String[] args)
    {
        SpringApplication.run(SmartHealthApplication.class, args);
        log.info("智慧健康启动");
    }

}
