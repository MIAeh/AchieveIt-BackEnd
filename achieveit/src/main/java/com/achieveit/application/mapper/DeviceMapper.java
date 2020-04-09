package com.achieveit.application.mapper;

import com.achieveit.application.entity.DeviceEntity;
import org.apache.ibatis.annotations.*;

import java.util.ArrayList;
import java.util.Date;

@Mapper
public interface DeviceMapper {

    @Select("SELECT deviceid FROM deviceidlist WHERE occupied=false")
    ArrayList<String> getDeviceIDList();

    @Update("UPDATE deviceidlist SET occupied=#{occupied} WHERE (deviceid=#{deviceID});")
    void updateDeviceIDList(String deviceID, Boolean occupied);

    @Select("SELECT devices.*, users.username FROM devices, users WHERE (devices.projectid=#{projectID} AND devices.userid=users.userid)")
    ArrayList<DeviceEntity> getDeviceList(String projectID);

    @Insert("INSERT INTO devices(deviceregisteruuid, deviceid, userid, projectid, duedate, returned) VALUES ((select uuid_in(md5(random()::text || clock_timestamp()::text)::cstring)), #{deviceID}, #{userID}, #{projectID}, #{dueDate}, false);")
    void registerDevice(String projectID, String userID, String deviceID, Date dueDate);

    @Update("UPDATE devices SET returned=true WHERE (deviceid=#{deviceID} AND projectid=#{projectID} AND userid=#{userID});")
    void returnDevice(String projectID, String userID, String deviceID);
}
