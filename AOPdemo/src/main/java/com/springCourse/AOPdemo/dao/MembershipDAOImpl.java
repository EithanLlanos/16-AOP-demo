package com.springCourse.AOPdemo.dao;

import org.springframework.stereotype.Repository;

@Repository
public class MembershipDAOImpl implements MembershipDAO {

    @Override
    public boolean addSillyMember() {
        System.out.println(getClass() + " DOING MY DB WORK: ADDING A MEMBERSHIP ACCOUNT");
        return false;
    }

    @Override
    public void goToSleep() {
        System.out.println(getClass() + ": Ima going to sleep know...");
    }
}
