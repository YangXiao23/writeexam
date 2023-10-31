package com.example;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;


/**
 * @author: yangxiao
 * @DATE 2023/10/21 0:28
 */
@SpringBootApplication
@EnableDiscoveryClient
public class LoginMain {
    public static void main(String[] args) {
        SpringApplication.run(LoginMain.class, args);
    }
}
