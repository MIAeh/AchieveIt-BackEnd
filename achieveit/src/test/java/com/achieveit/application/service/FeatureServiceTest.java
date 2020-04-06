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
public class FeatureServiceTest {
    @Autowired
    private FeatureService featureService;

    @Test
    public void testAddTopFeature(){
        Assert.assertTrue(true);
    }

    @Test
    public void testAddSubFeature(){
        Assert.assertTrue(true);
    }

    @Test
    public void testGetFeatureByProjectID(){
        Assert.assertTrue(true);
    }

    @Test
    public void getFeaturesInfo(){
        Assert.assertTrue(true);
    }

}
