package com.springCourse.AOPdemo.aspect;

import com.springCourse.AOPdemo.dao.Account;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.List;

@Aspect
@Component
@Order(2)
public class MyDemoLoggingAspect {

    @AfterThrowing(pointcut = "execution(* com.springCourse.AOPdemo.dao.AccountDAO.findAccounts(..))", throwing = "theExc")
    public void afterThrowingFindAccountsADVICE(JoinPoint joinPoint, Throwable theExc) {
//        print out which method we are advising on
        String method = joinPoint.getSignature().toShortString();
        System.out.println("\n=====>>> Executing @AfterThrowing on method: " + method);
//        Print out the results of the method call
        System.out.println("\n=====>>> The exception is: " + theExc);

    }

    //    Add new advice for @AfterReturning on the findAccounts method
    @AfterReturning(pointcut = "execution(* com.springCourse.AOPdemo.dao.AccountDAO.findAccounts(..))", returning = "result")
    public void afterReturningFindAccountsAdvice(JoinPoint joinPoint, List<Account> result) {
//        Print out which method we are advising on
        String method = joinPoint.getSignature().toShortString();
        System.out.println("\n=====>>> Executing @AfterReturning on method: " + method);
//        Print out the results of the method call
        System.out.println("\n=====>>> Result is: " + result);

//        Lets post-process the data
//        Convert the account names to uppercase
        convertAccountNamesToUpperCase(result);
        System.out.println("\n=====>>> Result is: " + result);
    }

    private void convertAccountNamesToUpperCase(List<Account> result) {
//        Loop Thru accounts
        for (Account tempAccount : result) {
//            get uppercase version of each name
            String theUpperName = tempAccount.getName().toUpperCase();

//            update the name on the account
            tempAccount.setName(theUpperName);
        }
    }


    @Before("com.springCourse.AOPdemo.dao.AopExpressions.forDaoPackageNoGetterSetter()")
    public void beforeAddAccountAdvice(JoinPoint theJoinPoint) {
        System.out.println("\n ====> Executing @Before advice on addAccount()");
//        Display method signature
        MethodSignature methodSignature = (MethodSignature) theJoinPoint.getSignature();
        System.out.println("Method: " + methodSignature);
//      Get args
        Object[] args = theJoinPoint.getArgs();
//      loop thru args
        for (Object tempArg : args) {
            System.out.println(tempArg);
            if (tempArg instanceof Account theAccount) {
//                DownCast and print Account specific stuff
                System.out.println("Account name: " + theAccount.getName());
                System.out.println("Account level: " + theAccount.getLevel());
            }

        }

    }
}
