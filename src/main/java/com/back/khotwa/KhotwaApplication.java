package com.back.khotwa;

import com.back.khotwa.security.MultiHttpSecurityConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import java.io.IOException;


@ComponentScan({"com.back.khotwa.controller.api","com.back.khotwa.service","com.back.khotwa.repository","com.back.khotwa.config"})
@ComponentScan(basePackageClasses = MultiHttpSecurityConfig.class)
@SpringBootApplication
public class KhotwaApplication {

    public static void main(String[] args)

    {
        SpringApplication.run(KhotwaApplication.class, args);
    }

}
