package com.achieveit.application.mapper;

import com.achieveit.application.entity.UserEntity;
import org.apache.ibatis.annotations.*;
import java.util.ArrayList;

import static org.apache.ibatis.type.JdbcType.VARCHAR;

/**
 * Mapper for UserEntity
 * @author Alevery, Felix
 */
@Mapper
public interface UserMapper {

    /**
     * 根据userID来获取用户信息
     * @param userId 所要获取的用户的userID
     * @return User
     */
    @Select("select * from \"users\" where userid =#{userId}")
    @Results({
            @Result(property = "userId", column = "userid", jdbcType = VARCHAR, javaType = java.lang.String.class),
            @Result(property = "userMail", column = "usermail"),
            @Result(property = "userName", column = "username"),
            @Result(property = "userPhone", column = "userphone"),
            @Result(property = "userPassword", column = "userpassword"),
            @Result(property = "userDepartment", column = "userdepartment"),
            @Result(property = "userPhone", column = "userphone")
    })
    UserEntity getUserInfoById(@Param("userId") String userId);

    /**
     * 根据userPhone来获取用户信息
     * @param userPhone 所要获取的用户的userPhone
     * @return User
     */
    @Select("select * from \"users\" where userphone = #{userPhone}")
    @Results({
            @Result(property = "userId", column = "userid", jdbcType = VARCHAR, javaType = java.lang.String.class),
            @Result(property = "userMail", column = "usermail"),
            @Result(property = "userName", column = "username"),
            @Result(property = "userPhone", column = "userphone"),
            @Result(property = "userPassword", column = "userpassword"),
            @Result(property = "userDepartment", column = "userdepartment"),
            @Result(property = "userPhone", column = "userphone")
    })
    UserEntity getUserInfoByPhone(@Param("userPhone") String userPhone);

    /**
     * 根据userMail来获取用户信息
     * @param userMail 所要获取的用户的userMail
     * @return User
     */
    @Select("select * from \"users\" where usermail = #{userMail}")
    @Results({
            @Result(property = "userId", column = "userid", jdbcType = VARCHAR, javaType = java.lang.String.class),
            @Result(property = "userMail", column = "usermail"),
            @Result(property = "userName", column = "username"),
            @Result(property = "userPhone", column = "userphone"),
            @Result(property = "userPassword", column = "userpassword"),
            @Result(property = "userDepartment", column = "userdepartment"),
            @Result(property = "userPhone", column = "userphone")
    })
    UserEntity getUserInfoByMail(@Param("userMail") String userMail);

    /**
     * 插入（注册）一位新的用户
     * @param userEntity 所要注册的user
     */
    @Insert("insert into \"user\"(userid,usermail,username,userpassword,userdepartment,userrole)" +
            " values(#{userId}::uuid, #{userMail},#{userName},#{userPassword},#{userDepartment},#{userRole})")
    void insertUser(UserEntity userEntity);

    /**
     * 根据Role获取所有用户
     * @param userRole 所要获取的users
     * @return affectedRows
     */
    @Select("select * from \"users\" where userrole = #{userRole}")
    ArrayList<UserEntity> getUsesByRole(@Param("userRole") int userRole);

    @Update("update users set userrole=#{userRole} where userid=#{userId}")
    int setUserRoleById(@Param("userId")String userId,@Param("userRole")int userRole);

}