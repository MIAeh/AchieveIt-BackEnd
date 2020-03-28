package com.achieveit.application.service;

import com.achieveit.application.annotation.Logged;
import com.achieveit.application.mapper.DeviceMapper;
import com.achieveit.application.wrapper.ResponseResult;
import com.achieveit.application.wrapper.ResultGenerator;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Client Service
 */
@Service
public class DeviceService {

    private final DeviceMapper deviceMapper;

    public DeviceService(DeviceMapper deviceMapper) {
        this.deviceMapper = deviceMapper;
    }

    @Logged
    public ResponseResult<List<String>> getDeviceIDList() {
        List<String> deviceIDs = deviceMapper.getDeviceIDList();
        return ResultGenerator.success(deviceIDs);
    }
}
