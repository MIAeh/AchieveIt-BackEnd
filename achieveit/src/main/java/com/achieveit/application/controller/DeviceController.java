package com.achieveit.application.controller;

import com.achieveit.application.annotation.Logged;
import com.achieveit.application.service.DeviceService;
import com.achieveit.application.wrapper.ResponseResult;
import org.springframework.web.bind.annotation.*;

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
        return deviceService.getDeviceIDList();
    }
}
