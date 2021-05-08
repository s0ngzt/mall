package com.example.mall;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.oas.annotations.EnableOpenApi;

@EnableOpenApi
@SpringBootApplication
public class MallApplication {

  public static void main(String[] args) {
    SpringApplication.run(MallApplication.class, args);
  }

}
