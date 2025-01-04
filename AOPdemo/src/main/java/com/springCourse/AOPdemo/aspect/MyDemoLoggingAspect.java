package com.springCourse.AOPdemo.aspect;

import com.springCourse.AOPdemo.dao.Account;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Order(2)
public class MyDemoLoggingAspect {
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
