package com.achieveit.application.service;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
@MapperScan("com.achieveit.application.mapper")
public class RiskServiceTest {
    @Autowired
    private RiskService riskService;

    @Test
    public void testAddRisk(){
        Assert.assertTrue(true);
    }

    @Test
    public void testGetRisks(){
        Assert.assertTrue(true);
    }

    @Test
    public void testChangeRiskStatus(){
        Assert.assertTrue(true);
    }

    @Test
    public void testAddRiskHolders(){
        Assert.assertTrue(true);
    }

    @Test
    public void testGetRiskHolders(){
        Assert.assertTrue(true);
    }

}
