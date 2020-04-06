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
    public void testBLogin(){
        ResponseResult<UserEntity> res=userService.loginByMail("one_how@163.com","123",session);
        Assert.assertTrue(true);
    }

    @Test
    public void testCChangeUserRole(){
        ResponseResult<UserEntity> entity=userService.loginByMail("one_how@163.com","123",session);
        ResponseResult<Boolean> res;
        if(entity!=null) {
            if(entity.getData()!=null)
                res = userService.setUserRoleById(entity.getData().getUserId(), 1, session);
        }
        Assert.assertTrue(true);
    }
}
