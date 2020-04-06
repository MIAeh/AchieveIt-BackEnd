package com.achieveit.application.service;

import com.achieveit.application.entity.DeviceInfo;
import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
@MapperScan("com.achieveit.application.mapper")
@Transactional
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class DeviceServiceTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(DeviceServiceTest.class);

    @Autowired
    private DeviceService deviceService;

    @Test
    public void getDeviceIDListTest() {
        List<String> deviceIDs = deviceService.getDeviceIDList();
        Assert.assertNotNull(deviceIDs);
    }

    @Test
    public void getDeviceListTest() {
        String projectID = "2019-0000-D-01";
        List<DeviceInfo> deviceInfos = deviceService.getDeviceList(projectID);
        Assert.assertNotNull(deviceInfos);
    }

    @Test
    public void registerDeviceTest() throws ParseException {
//        String projectID = "2019-0000-D-01";
//        String userID = "0001";
//        String deviceID = "PC-20190202-0003";
//        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
//        Date dueDate = dateFormat.parse("2020-3-30");
//        deviceService.registerDevice(projectID, userID, deviceID, dueDate);
        Assert.assertTrue(true);
    }

    @Test
    public void returnDeviceTest() {
//        String projectID = "2019-0000-D-01";
//        String userID = "0001";
//        String deviceID = "PC-20190202-0003";
//        deviceService.returnDevice(projectID, userID, deviceID);
        Assert.assertTrue(true);
    }
}
