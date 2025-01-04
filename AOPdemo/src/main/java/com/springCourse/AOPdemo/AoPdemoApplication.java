package com.springCourse.AOPdemo;

import com.springCourse.AOPdemo.dao.Account;
import com.springCourse.AOPdemo.dao.AccountDAO;
import com.springCourse.AOPdemo.dao.MembershipDAO;
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
    public CommandLineRunner commandLineRunner(AccountDAO accountDAO, MembershipDAO membershipDAO) {
        return runner -> {
            demoTheBeforeAdvice(accountDAO, membershipDAO);
        };
    }

    private void demoTheBeforeAdvice(AccountDAO accountDAO, MembershipDAO memberShipDAO) {
//		Call the business method
        Account myAccount = new Account();
        accountDAO.addAccount(myAccount, true);
        accountDAO.doWork();

//      Call the accountDAO getter/setter methods
        accountDAO.setName("foobar");
        accountDAO.setServiceCode("silver");

        String name = accountDAO.getName();
        String serviceCode = accountDAO.getServiceCode();
//		Call the membership business method
        memberShipDAO.addSillyMember();
        memberShipDAO.goToSleep();
    }
}
