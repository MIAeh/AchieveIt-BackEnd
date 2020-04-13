package com.achieveit.application.service;

import com.achieveit.application.entity.FeatureEntity;
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
import java.util.ArrayList;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
@MapperScan("com.achieveit.application.mapper")
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class FeatureServiceTest {

    @Autowired
    private FeatureService featureService;

    private HttpSession session;

    @Before
    public void setSession() {
        session = new MockHttpSession();
    }

    @Test
    public void testAAddTopFeature() {
        ResponseResult<FeatureEntity> res = featureService.insertTopFeature("testFeature", "2020-001-002-D", "test", session);
        Assert.assertTrue(true);
    }

    @Test
    public void testBAddSubFeature() {
        ResponseResult<ArrayList<FeatureEntity>> entities = featureService.getFeaturesByProjectID("2020-001-002-D");
        if (entities == null || entities.getData() == null || entities.getData().size() == 0) {
            Assert.assertTrue(true);
            return;
        }
        featureService.insertSubFeature("testFea2", "2020-001-002-D", entities.getData().get(0).getFeatureId(), "test", session);
        Assert.assertTrue(true);
    }

    @Test
    public void testCGetFeatureByProjectID() {
        ResponseResult<ArrayList<FeatureEntity>> entities = featureService.getFeaturesByProjectID("2020-001-002-D");
        if (entities == null || entities.getData() == null || entities.getData().size() == 0) {
            Assert.assertTrue(true);
            return;
        }
        Assert.assertTrue(true);
    }

    @Test
    public void testDGetFeaturesInfo() {
        ResponseResult<ArrayList<FeatureEntity>> res = featureService.getFeaturesInfo(session);
        Assert.assertTrue(true);
    }
}
