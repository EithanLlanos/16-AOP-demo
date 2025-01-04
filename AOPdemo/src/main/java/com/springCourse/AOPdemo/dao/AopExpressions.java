package com.springCourse.AOPdemo.dao;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

@Aspect
public class AopExpressions {
    //    This is where we add all of our related advices for logging
    //    Lets starts with a @Before advice

    //    Add pointcut
    @Pointcut("execution(* com.springCourse.AOPdemo.dao.*.*(..))")
    public void forDaoPackage() {
    }

    //    Add pointcut for setter methods
    @Pointcut("execution(* com.springCourse.AOPdemo.dao.*.get*())")
    public void getter() {
    }

    //    Add pointcut for getter methods
    @Pointcut("execution(* com.springCourse.AOPdemo.dao.*.set*(..))")
    public void setter() {
    }

    //    Create pointcut including package to exclude getter and setter methods
    @Pointcut("forDaoPackage() && !(getter() || setter())")
    public void forDaoPackageNoGetterSetter() {
    }


}
