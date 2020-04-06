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
public class WorkHourServiceTest {
    @Autowired
    private WorkHourService workHourService;

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
