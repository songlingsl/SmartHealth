package com.smarthealth.diningroom;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@Slf4j
@EnableCaching
@SpringBootApplication
public class SmartHealthApplication {

    public static void main(String[] args)
    {
        SpringApplication.run(SmartHealthApplication.class, args);
        log.info("智慧健康启动");
    }

}
