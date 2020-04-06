package com.achieveit.application.service;

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
public class WorkHourServiceTest {
    @Autowired
    private WorkHourService workHourService;

    private HttpSession session;

    @Before
    public void setSession(){
        session = new MockHttpSession();
    }

    @Test
    public void testAddWorkHour(){
        Assert.assertTrue(true);
    }

    @Test
    public void testApproveWorkHour(){
        Assert.assertTrue(true);
    }

    @Test
    public void testGetAllWorkHours(){
        Assert.assertTrue(true);
    }

}
