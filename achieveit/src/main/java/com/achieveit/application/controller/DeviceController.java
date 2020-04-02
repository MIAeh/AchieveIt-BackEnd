package com.achieveit.application.controller;

import com.achieveit.application.annotation.Logged;
import com.achieveit.application.entity.DeviceEntity;
import com.achieveit.application.entity.DeviceInfo;
import com.achieveit.application.service.DeviceService;
import com.achieveit.application.wrapper.ResponseResult;
import com.achieveit.application.wrapper.ResultGenerator;
import org.springframework.web.bind.annotation.*;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/device")
public class DeviceController {

    private final DeviceService deviceService;

    public DeviceController(DeviceService deviceService) {
        this.deviceService = deviceService;
    }

    @CrossOrigin
    @Logged
    @GetMapping("/getDeviceIDList")
    public ResponseResult<List<String>> getDeviceIDList() {
        List<String> deviceIDs = deviceService.getDeviceIDList();
        return ResultGenerator.success(deviceIDs);
    }

    @CrossOrigin
    @Logged({"projectID"})
    @GetMapping("/getDeviceList")
    public ResponseResult<List<DeviceInfo>> getDeviceList(@RequestParam("projectID") String projectID) {
        List<DeviceInfo> deviceInfos = deviceService.getDeviceList(projectID);
        return ResultGenerator.success(deviceInfos);
    }

    @CrossOrigin
    @Logged({"projectID", "userID", "deviceID", "dueDate"})
    @PostMapping("/registerDevice")
    public ResponseResult registerDevice(@RequestParam("projectID") String projectID, @RequestParam("userID") String userID, @RequestParam("deviceID") String deviceID, @RequestParam("dueDate") String dueDate) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        try {
            deviceService.registerDevice(projectID, userID, deviceID, dateFormat.parse(dueDate));
            return ResultGenerator.success();
        } catch (ParseException e) {
            e.printStackTrace();
            return ResultGenerator.error(e.getMessage());
        }
    }

    @CrossOrigin
    @Logged({"projectID", "userID", "deviceID"})
    @PostMapping("/returnDevice")
    public ResponseResult returnDevice(@RequestParam("projectID") String projectID, @RequestParam("userID") String userID, @RequestParam("deviceID") String deviceID) {
        deviceService.returnDevice(projectID, userID, deviceID);
        return ResultGenerator.success();
    }
}
