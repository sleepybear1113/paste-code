package cn.sleepybear.pastecode;

import cn.sleepybear.pastecode.utils.SpringContextUtil;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * @author xjx
 */
@SpringBootApplication
@MapperScan(basePackages = {"cn.sleepybear.pastecode.mapper"})
public class PasteCodeApplication {

    public static void main(String[] args) {
        SpringApplicationBuilder builder = new SpringApplicationBuilder(PasteCodeApplication.class);
        ConfigurableApplicationContext context = builder.run(args);
        SpringContextUtil.setApplicationContext(context);
    }

}
