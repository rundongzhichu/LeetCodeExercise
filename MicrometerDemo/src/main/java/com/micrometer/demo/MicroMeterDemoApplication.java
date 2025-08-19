package com.micrometer.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication(exclude = {
        DataSourceAutoConfiguration.class,
        // 添加其他需要排除的数据库配置类
})
@EnableScheduling
public class MicroMeterDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(MicroMeterDemoApplication.class, args);
    }

}
