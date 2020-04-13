package com.achieveit.application.mapper;

import com.achieveit.application.entity.ClientInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * Client Mapper
 */
@Mapper
public interface ClientMapper {

    /**
     * 通过客户ID获取客户信息
     *
     * @param clientID 客户ID
     * @return ClientInfo
     */
    @Select("SELECT * FROM client WHERE clientID = #{clientID} and deleted=false")
    ClientInfo getClientInfoByID(@Param("clientID") String clientID);

}
