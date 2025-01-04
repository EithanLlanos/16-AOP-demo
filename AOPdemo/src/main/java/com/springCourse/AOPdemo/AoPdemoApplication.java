package com.springCourse.AOPdemo;

import com.springCourse.AOPdemo.dao.AccountDAO;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class AoPdemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(AoPdemoApplication.class, args);
    }

    @Bean
    public CommandLineRunner commandLineRunner(AccountDAO accountDAO) {
        return runner -> {
            demoTheBeforeAdvice(accountDAO);
        };
    }

    private void demoTheBeforeAdvice(AccountDAO accountDAO) {
//		Call the business method
        accountDAO.addAccount();
//		Call it again!
        System.out.println("\nCalling it again!\n");
        accountDAO.addAccount();
    }
}
