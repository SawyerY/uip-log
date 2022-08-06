package com.example.uiplog;

import com.weds.framework.core.annotation.MyBatisDao;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

@ServletComponentScan
@MapperScan(basePackages ={"com.example.uiplog"}, annotationClass = MyBatisDao.class)
@SpringBootApplication
@EnableAsync
@EnableScheduling //定时器
public class UipLogApplication {

    public static void main(String[] args) {
        SpringApplication.run(UipLogApplication.class, args);
    }

}
