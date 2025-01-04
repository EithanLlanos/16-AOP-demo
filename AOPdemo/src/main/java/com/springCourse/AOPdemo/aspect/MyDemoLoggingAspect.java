package com.springCourse.AOPdemo.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class MyDemoLoggingAspect {
    //    This is where we add all of our related advices for logging
    //    Lets starts with a @Before advice

    @Before("execution(* add*())")
    public void beforeAddAccountAdvice() {
        System.out.println("\n ====> Executing @Before advice on addAccount()");
    }
}
