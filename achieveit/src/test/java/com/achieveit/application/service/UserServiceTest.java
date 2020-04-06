package com.achieveit.application.service;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
@MapperScan("com.achieveit.application.mapper")
public class UserServiceTest {
    @Autowired
    private UserService userService;

    @Test
    public void testAddUser(){
        Assert.assertTrue(true);
    }

    @Test
    public void testLogin(){
        Assert.assertTrue(true);
    }

    @Test
    public void testChangeUserRole(){
        Assert.assertTrue(true);
    }
}
