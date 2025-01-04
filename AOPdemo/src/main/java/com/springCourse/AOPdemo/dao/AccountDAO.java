package com.springCourse.AOPdemo.dao;

import java.util.List;

public interface AccountDAO {

    void addAccount(Account theAccount, boolean vipFlag);

    boolean doWork();

    String getName();

    void setName(String name);

    String getServiceCode();

    void setServiceCode(String serviceCode);

    List<Account> findAccounts();
}
