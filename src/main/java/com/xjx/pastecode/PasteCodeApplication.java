package com.xjx.pastecode;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author xjx
 */
@SpringBootApplication
@MapperScan(basePackages = {"com.xjx.pastecode.mapper"})
public class PasteCodeApplication {

    public static void main(String[] args) {
        SpringApplication.run(PasteCodeApplication.class, args);
    }

}
