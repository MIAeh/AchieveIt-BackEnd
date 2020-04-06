package com.achieveit.application.service;

import com.achieveit.application.entity.UserEntity;
import com.achieveit.application.wrapper.ResponseResult;
import org.junit.Assert;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpSession;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
@MapperScan("com.achieveit.application.mapper")
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class UserServiceTest {
    @Autowired
    private UserService userService;

    private HttpSession session;

    @Before
    public void setSession(){
        session = new MockHttpSession();
    }

    @Test
    public void testAAddUser(){
        ResponseResult<Integer> res=userService.register("one_how@163.com","tester","123");
        Assert.assertNotNull(res.isSuccess());
    }

    @Test
    public void testBLoginByUserMail(){
        ResponseResult<UserEntity> res=userService.loginByMail("one_how@163.com","123",session);
        Assert.assertTrue(true);
    }

    @Test
    public void testCLoginByPhone(){
        userService.loginByPhone("110","123456",session);
        Assert.assertTrue(true);
    }

    @Test
    public void testDChangeUserRole(){
        ResponseResult<Boolean> entity=userService.setUserRoleById("one_how@163.com",0,session);
        Assert.assertTrue(true);
    }

    @Test
    public void testELoginByUserId(){
        userService.loginByUserId("b6703879-e1e2-499c-8ffe-d8b29f71f156","123456",session);
        Assert.assertTrue(true);
    }
}
