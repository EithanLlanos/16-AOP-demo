package com.springCourse.AOPdemo.dao;

import org.springframework.stereotype.Repository;

@Repository
public class MembershipDAOImpl implements MembershipDAO {

    @Override
    public void addSillyMember() {
        System.out.println(getClass() + " DOING MY DB WORK: ADDING A MEMBERSHIP ACCOUNT");
    }
}
