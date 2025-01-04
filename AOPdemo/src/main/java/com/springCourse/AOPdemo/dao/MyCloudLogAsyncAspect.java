package com.springCourse.AOPdemo.dao;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class MyCloudLogAsyncAspect {
    @Before("com.springCourse.AOPdemo.dao.AopExpressions.forDaoPackageNoGetterSetter()")
    public void logToCloudAsync() {
        System.out.println("\n ====> Logging to Cloud in async fashion");

    }
}
