package com.achieveit.application.service;

import org.junit.Before;
import org.junit.FixMethodOrder;
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
@MapperScan("com.achieveit.application.mapper")
@Transactional
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class WorkHourServiceTest {
    @Autowired
    private WorkHourService workHourService;

    private HttpSession session;

    private String workHourId;

    @Before
    public void setSession() {
        session = new MockHttpSession();
    }

//    @Test
//    public void testAddWorkHour(){
//        ResponseResult<WorkHourEntity> res=workHourService.applyWordHour("none","testFeature","testActivity",new Date(System.currentTimeMillis()),new Date(System.currentTimeMillis()+1000000));
//        if(res!=null&&res.getData()!=null)
//            workHourId=res.getData().getWorkHourId();
//        Assert.assertTrue(true);
//    }
//
//    @Test
//    public void testApproveWorkHour(){
//        workHourService.approveWordHour(workHourId,"none");
//        Assert.assertTrue(true);
//    }
//
//    @Test
//    public void testGetAllWorkHours(){
//        workHourService.getAllWorkHours();
//        Assert.assertTrue(true);
//    }
//
//    @Test
//    public void testGetWorkHourByStatus(){
//        workHourService.getWorkHourByStatus(0);
//        Assert.assertTrue(true);
//    }
//
//    @Test
//    public void testGetMyWorkHourById(){
//        workHourService.getMyWorkHourById("none");
//        Assert.assertTrue(true);
//    }
}
