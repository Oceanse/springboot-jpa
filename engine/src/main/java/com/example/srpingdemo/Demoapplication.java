package com.example.srpingdemo;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;

@SpringBootApplication
public class Demoapplication {
    public static void main(String[] args) {

        SpringApplication.run(Demoapplication.class,args);
    }


/*  @Bean
    public CommandLineRunner beanInfo(ApplicationContext ctx){
        return args -> {
            int beanDefinitionCount = ctx.getBeanDefinitionCount();
            System.out.println("beanDefinitionCount="+beanDefinitionCount);
            String[] beanDefinitionNames = ctx.getBeanDefinitionNames();
            for (String beanDefinitionName : beanDefinitionNames) {
                System.out.println(beanDefinitionName);
            }
        };

    }*/




}
