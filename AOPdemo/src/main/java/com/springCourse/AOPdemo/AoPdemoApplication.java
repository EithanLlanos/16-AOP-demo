package com.springCourse.AOPdemo;

import com.springCourse.AOPdemo.dao.Account;
import com.springCourse.AOPdemo.dao.AccountDAO;
import com.springCourse.AOPdemo.dao.MembershipDAO;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class AoPdemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(AoPdemoApplication.class, args);
    }

    @Bean
    public CommandLineRunner commandLineRunner(AccountDAO accountDAO, MembershipDAO membershipDAO) {
        return runner -> {
//            demoTheBeforeAdvice(accountDAO, membershipDAO);
//            demoTheAfterReturningAdvice(accountDAO);
            demoTheAfterThrowingAdvice(accountDAO);
        };
    }

    private void demoTheAfterThrowingAdvice(AccountDAO accountDAO) {
        List<Account> listAccounts = null;
        try {
//            add bolean flag to simulate exceptions
            boolean tripWire = true;
            listAccounts = accountDAO.findAccounts(tripWire);
        } catch (Exception exc) {
            System.out.println("\n\nMain Programs: ... caught exception: " + exc);
        }
        System.out.println("\n\nMain Program: demoTheAfterThrowingAdvice");
        System.out.println("---------------------------");
        System.out.println(listAccounts);
        System.out.println("\n");
    }

    private void demoTheAfterReturningAdvice(AccountDAO accountDAO) {
        List<Account> listAccounts = accountDAO.findAccounts();

        System.out.println("\n\nMain Program: demoTheAfterReturningAdvice");
        System.out.println("---------------------------");
        System.out.println(listAccounts);
        System.out.println("\n");
    }

    private void demoTheBeforeAdvice(AccountDAO accountDAO, MembershipDAO memberShipDAO) {
//		Call the business method
        Account myAccount = new Account();
        myAccount.setName("Madhu");
        myAccount.setLevel("Platinum");
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
