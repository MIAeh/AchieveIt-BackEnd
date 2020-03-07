package com.achieveit.application.mapper;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.ArrayList;

/**
 * Client Mapper
 */
public interface ProjectMapper {

    /**
     * 通过客户ID获取客户信息
     * @return ArrayList of ProjectIDs
     */
    @Select("SELECT * FROM projectidlist")
    ArrayList<String> getClientInfoByID();

}
