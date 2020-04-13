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

    /**
     * 获取（未占用）设备列表
     *
     * @return （未占用）设备列表
     */
    @Logged
    public List<String> getDeviceIDList() {
        return deviceMapper.getDeviceIDList();
    }

    /**
     * 通过项目ID获取设备使用情况列表
     *
     * @param projectID 项目ID
     * @return 设备使用情况列表
     */
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

    /**
     * 登记设备使用
     * 需要对已归档的项目进行Post控制
     *
     * @param projectID 项目ID
     * @param userID    设备使用者ID
     * @param deviceID  设备ID
     * @param dueDate   归还日期
     * @param session   会话
     * @return success/error
     */

    /**
     * 登记设备使用
     *
     * @param projectID 项目ID
     * @param userID    设备使用者ID
     * @param deviceID  设备ID
     * @param dueDate   归还日期
     * @param session   会话
     * @throws AchieveitException 获取不到loginUserID导致SESSION_ERROR/成员存在导致QUERY_ERROR/成员角色权限错误导致ROLE_ERROR
     */
    @Transactional
    @Logged({"projectID", "userID", "deviceID", "dueDate", "session"})
    public void registerDevice(String projectID, String userID, String deviceID, Date dueDate, HttpSession session) throws AchieveitException {
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

    /**
     * 归还设备
     *
     * @param projectID 项目ID
     * @param userID    设备使用者ID
     * @param deviceID  设备ID
     * @param session   会话
     * @throws AchieveitException 获取不到loginUserID导致SESSION_ERROR/成员存在导致QUERY_ERROR/成员角色权限错误导致ROLE_ERROR
     */
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
