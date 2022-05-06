package cn.xiejx.pastecode;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author xjx
 */
@SpringBootApplication
@MapperScan(basePackages = {"cn.xiejx.pastecode.mapper"})
public class PasteCodeApplication {

    public static void main(String[] args) {
        SpringApplication.run(PasteCodeApplication.class, args);
    }

}
