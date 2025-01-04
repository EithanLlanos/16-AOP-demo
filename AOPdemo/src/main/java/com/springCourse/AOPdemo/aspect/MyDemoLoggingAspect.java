package com.springCourse.AOPdemo.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class MyDemoLoggingAspect {
    //    This is where we add all of our related advices for logging
    //    Lets starts with a @Before advice

    //    Add pointcut
    @Pointcut("execution(* com.springCourse.AOPdemo.dao.*.*(..))")
    private void forDaoPackage() {
    }

    //    Add pointcut for setter methods
    @Pointcut("execution(* com.springCourse.AOPdemo.dao.*.get*())")
    private void getter() {
    }

    //    Add pointcut for getter methods
    @Pointcut("execution(* com.springCourse.AOPdemo.dao.*.set*(..))")
    private void setter() {
    }

    //    Create pointcut including package to exclude getter and setter methods
    @Pointcut("forDaoPackage() && !(getter() || setter())")
    private void forDaoPackageNoGetterSetter() {
    }


    @Before("forDaoPackageNoGetterSetter()")
    public void beforeAddAccountAdvice() {
        System.out.println("\n ====> Executing @Before advice on addAccount()");
    }

    @Before("forDaoPackageNoGetterSetter()")
    public void performApiAnalytics() {
        System.out.println("\n ====> Performing API analytics");
    }
}
