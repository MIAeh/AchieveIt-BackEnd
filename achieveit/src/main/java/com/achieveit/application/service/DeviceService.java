package com.achieveit.application.service;

import com.achieveit.application.annotation.Logged;
import com.achieveit.application.entity.DeviceEntity;
import com.achieveit.application.entity.DeviceInfo;
import com.achieveit.application.mapper.DeviceMapper;
import com.achieveit.application.wrapper.ResponseResult;
import com.achieveit.application.wrapper.ResultGenerator;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.Date;
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

    @Logged({"projectID"})
    public ResponseResult<List<DeviceInfo>> getDeviceList(String projectID) {
        List<DeviceEntity> deviceEntities = deviceMapper.getDeviceList(projectID);
        List<DeviceInfo> deviceInfos = new ArrayList<>();
        for (DeviceEntity deviceEntity : deviceEntities) {
            DeviceInfo deviceInfo = new DeviceInfo(deviceEntity);
            deviceInfos.add(deviceInfo);
        }
        return ResultGenerator.success(deviceInfos);
    }

    @Transactional
    @Logged({"projectID", "userID", "deviceID"})
    public ResponseResult registerDevice(String projectID, String userID, String deviceID, Date dueDate) {
        deviceMapper.updateDeviceIDList(deviceID, true);
        deviceMapper.registerDevice(projectID, userID, deviceID, dueDate);
        return ResultGenerator.success();
    }

    @Logged({"projectID", "userID", "deviceID"})
    public ResponseResult returnDevice(String projectID, String userID, String deviceID) {
        deviceMapper.updateDeviceIDList(deviceID, false);
        deviceMapper.returnDevice(projectID, userID, deviceID);
        return ResultGenerator.success();
    }

}
