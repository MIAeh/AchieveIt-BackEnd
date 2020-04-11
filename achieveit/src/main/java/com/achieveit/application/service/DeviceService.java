package com.achieveit.application.service;

import com.achieveit.application.annotation.Logged;
import com.achieveit.application.entity.DeviceEntity;
import com.achieveit.application.entity.DeviceInfo;
import com.achieveit.application.entity.MemberEntity;
import com.achieveit.application.entity.MemberInfo;
import com.achieveit.application.enums.ErrorCode;
import com.achieveit.application.enums.MemberRoles;
import com.achieveit.application.exception.AchieveitException;
import com.achieveit.application.mapper.DeviceMapper;
import com.achieveit.application.mapper.ProjectMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Client Service
 */
@Service
public class DeviceService {

    private final DeviceMapper deviceMapper;

    private final ProjectMapper projectMapper;

    public DeviceService(DeviceMapper deviceMapper, ProjectMapper projectMapper) {
        this.deviceMapper = deviceMapper;
        this.projectMapper = projectMapper;
    }

    @Logged
    public List<String> getDeviceIDList() {
        return deviceMapper.getDeviceIDList();
    }

    @Logged({"projectID"})
    public List<DeviceInfo> getDeviceList(String projectID) {
        List<DeviceEntity> deviceEntities = deviceMapper.getDeviceList(projectID);
        List<DeviceInfo> deviceInfos = new ArrayList<>();
        for (DeviceEntity deviceEntity : deviceEntities) {
            DeviceInfo deviceInfo = new DeviceInfo(deviceEntity);
            deviceInfos.add(deviceInfo);
        }
        return deviceInfos;
    }

    @Transactional
    @Logged({"projectID", "userID", "deviceID", "dueDate", "session"})
    public void registerDevice(String projectID, String userID, String deviceID, Date dueDate, HttpSession session) {
        // member role authorization
        String loginUserID = (String) session.getAttribute("userId");
        if (loginUserID == null) {
            throw new AchieveitException(ErrorCode.SESSION_ERROR);
        }
        MemberEntity memberEntity = projectMapper.getMemberByID(projectID, loginUserID);
        if (memberEntity == null) {
            throw new AchieveitException(ErrorCode.QUERY_ERROR);
        }
        MemberInfo memberInfo = new MemberInfo(memberEntity);
        if (!memberInfo.getMemberRole().contains(MemberRoles.PROPERTY_MANAGER.getRole())) {
            throw new AchieveitException(ErrorCode.ROLE_ERROR);
        }
        // register device
        deviceMapper.updateDeviceIDList(deviceID, true);
        deviceMapper.registerDevice(projectID, userID, deviceID, dueDate);
    }

    @Transactional
    @Logged({"projectID", "userID", "deviceID", "session"})
    public void returnDevice(String projectID, String userID, String deviceID, HttpSession session) {
        // member role authorization
        String loginUserID = (String) session.getAttribute("userId");
        if (loginUserID == null) {
            throw new AchieveitException(ErrorCode.SESSION_ERROR);
        }
        MemberEntity memberEntity = projectMapper.getMemberByID(projectID, loginUserID);
        if (memberEntity == null) {
            throw new AchieveitException(ErrorCode.QUERY_ERROR);
        }
        MemberInfo memberInfo = new MemberInfo(memberEntity);
        if (!memberInfo.getMemberRole().contains(MemberRoles.PROPERTY_MANAGER.getRole())) {
            throw new AchieveitException(ErrorCode.ROLE_ERROR);
        }
        // return device
        deviceMapper.updateDeviceIDList(deviceID, false);
        deviceMapper.returnDevice(projectID, userID, deviceID);
    }
}
