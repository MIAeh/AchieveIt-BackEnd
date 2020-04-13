package com.achieveit.application.controller;

import com.achieveit.application.annotation.Logged;
import com.achieveit.application.annotation.PostControl;
import com.achieveit.application.entity.DeviceInfo;
import com.achieveit.application.service.DeviceService;
import com.achieveit.application.wrapper.ResponseResult;
import com.achieveit.application.wrapper.ResultGenerator;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

@RestController
@RequestMapping("/device")
public class DeviceController {

    private final DeviceService deviceService;

    public DeviceController(DeviceService deviceService) {
        this.deviceService = deviceService;
    }

    /**
     * 获取（未占用）设备列表
     *
     * @return （未占用）设备列表
     */
    @CrossOrigin
    @Logged
    @GetMapping("/getDeviceIDList")
    public ResponseResult<List<String>> getDeviceIDList() {
        List<String> deviceIDs = deviceService.getDeviceIDList();
        return ResultGenerator.success(deviceIDs);
    }

    /**
     * 通过项目ID获取设备使用情况列表
     *
     * @param projectID 项目ID
     * @return 设备使用情况列表
     */
    @CrossOrigin
    @Logged({"projectID"})
    @GetMapping("/getDeviceList")
    public ResponseResult<List<DeviceInfo>> getDeviceList(@RequestParam("projectID") String projectID) {
        List<DeviceInfo> deviceInfos = deviceService.getDeviceList(projectID);
        return ResultGenerator.success(deviceInfos);
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
    @PostControl
    @CrossOrigin
    @Logged({"projectID", "userID", "deviceID", "dueDate", "session"})
    @PostMapping("/registerDevice")
    public ResponseResult registerDevice(@RequestParam("projectID") String projectID, @RequestParam("userID") String userID, @RequestParam("deviceID") String deviceID, @RequestParam("dueDate") String dueDate, HttpSession session) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        try {
            deviceService.registerDevice(projectID, userID, deviceID, dateFormat.parse(dueDate), session);
            return ResultGenerator.success();
        } catch (ParseException e) {
            e.printStackTrace();
            return ResultGenerator.error(e.getMessage());
        }
    }

    /**
     * 归还设备
     * 需要对已归档的项目进行Post控制
     *
     * @param projectID 项目ID
     * @param userID    设备使用者ID
     * @param deviceID  设备ID
     * @param session   会话
     * @return success/error
     */
    @PostControl
    @CrossOrigin
    @Logged({"projectID", "userID", "deviceID", "session"})
    @PostMapping("/returnDevice")
    public ResponseResult returnDevice(@RequestParam("projectID") String projectID, @RequestParam("userID") String userID, @RequestParam("deviceID") String deviceID, HttpSession session) {
        deviceService.returnDevice(projectID, userID, deviceID, session);
        return ResultGenerator.success();
    }
}
