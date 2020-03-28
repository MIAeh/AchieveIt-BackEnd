package com.achieveit.application.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.ArrayList;

/**
 * Client Mapper
 */
@Mapper
public interface DeviceMapper {

    @Select("SELECT deviceid FROM deviceidlist WHERE occupied=false")
    ArrayList<String> getDeviceIDList();

}
