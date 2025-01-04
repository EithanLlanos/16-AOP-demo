package com.springCourse.AOPdemo.aspect;

import com.springCourse.AOPdemo.dao.Account;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.List;

@Aspect
@Component
@Order(2)
public class MyDemoLoggingAspect {

    @Around("execution(* com.springCourse.AOPdemo.service.*.getFortune(..))")
    public Object aroundGetFortune(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
//        print out which method we are advising on

        String method = proceedingJoinPoint.getSignature().toShortString();
        System.out.println("\n=====>>> Executing @Around on method: " + method);
//        get begin timestamp
        long begin = System.currentTimeMillis();
//        execute the method
        Object result = null;
        try {
            result = proceedingJoinPoint.proceed();
        } catch (Exception exc) {
//            Log the exception
            System.out.println(exc.getMessage());
//            Give a custom message
            result = "Major accident! But no worries";
        }

//        end timestamp
        long end = System.currentTimeMillis();
//        calculate duration and display it
        long duration = end - begin;
        System.out.println("\n ====> Duration: " + duration / 1000.0 + "seconds");
        return result;
    }

    @After("execution(* com.springCourse.AOPdemo.dao.AccountDAO.findAccounts(..))")
    public void afterFinallyFindAccountsAdvice(JoinPoint joinPoint) {
//        print out which method we are advising on
        String method = joinPoint.getSignature().toShortString();
        System.out.println("\n=====>>> Executing @After on method: " + method);

    }

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
