package com.yhq.IndexTestDemo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.yhq.IndexTestDemo.service.TestService;

/**
 * Hello world!
 *
 */
@SpringBootApplication
@MapperScan("com.yhq.IndexTestDemo.dao")
public class App {


	public static void main(String[] args) {
		SpringApplication.run(App.class, args);
	}
}
