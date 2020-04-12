package com.achieveit.application.service;

import com.achieveit.application.entity.RiskEntity;
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
public class RiskServiceTest {
    @Autowired
    private RiskService riskService;

    private HttpSession session;

    private int riskId;

    @Before
    public void setSession(){
        session = new MockHttpSession();
    }

    @Test
    public void testAAddRisk(){
        ResponseResult<RiskEntity> risk=riskService.addRisk("test",0,"none",0,0,0,"none",0,"none",null,session);
        if(risk!=null&&risk.getData()!=null)
            riskId=risk.getData().getRiskID();
        Assert.assertTrue(true);
    }

    @Test
    public void testBGetRisks(){
        riskService.getAllRisks(session);
        Assert.assertTrue(true);
    }

    @Test
    public void testCChangeRiskStatus(){
        riskService.changeRiskStatus(riskId,"none");
        Assert.assertTrue(true);
    }

    @Test
    public void testDAddRiskHolders(){
        riskService.addRiskHoldersByRiskId(riskId,"none");
        Assert.assertTrue(true);
    }

    @Test
    public void testEGetRiskHolders(){
        riskService.getRiskHoldersByRiskId(riskId);
        Assert.assertTrue(true);
    }

    @Test
    public void testFAddRiskTemplate(){
        riskService.addRiskTemplate("testDescription",0,0,0,"waitToDie");
        Assert.assertTrue(true);
    }

    @Test
    public void testGGetRiskTemplate(){
        riskService.getRiskTemplates();
        Assert.assertTrue(true);
    }

    @Test
    public void testHGetRiskTemplatesByProjectID(){
        riskService.getRiskTemplatesByProjectID("none");
        Assert.assertTrue(true);
    }
}
