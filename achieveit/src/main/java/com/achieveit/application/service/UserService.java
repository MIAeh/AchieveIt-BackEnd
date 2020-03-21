package com.achieveit.application.service;

import com.achieveit.application.wrapper.ResponseResult;
import com.achieveit.application.wrapper.ResultGenerator;
import com.achieveit.application.entity.UserEntity;
import com.achieveit.application.mapper.UserMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;

@Service
public class UserService {

    /**
     * Mapper for user
     */
    private final UserMapper userMapper;

    /**
     * Logger
     */
    private final Logger logger = LoggerFactory.getLogger(UserService.class);

    @Autowired
    public UserService(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    /**
     * Avoid using magic value
     */
    private static final String IS_LOGIN = "isLogin";
    private static final String INNER_ERROR = "Inner Error!";

    /**
     * 用户注册
     * @param userMail 邮箱
     * @param userName 用户名
     * @param userPassword 密码
     * @return 是否成功注册的Response消息
     */
    @Transactional(rollbackFor = Exception.class)
    public ResponseResult<Integer> register(String userMail, String userName, String userPassword) {
        UserEntity userEntity = userMapper.getUserInfoByMail(userMail);
        if (userEntity != null) {
            return ResultGenerator.error("account has existed!");
        }
        userEntity = new UserEntity(userMail, userName, userPassword);
        userMapper.insertUser(userEntity);
        logger.info(userEntity.getUserId() + " login !");
        return ResultGenerator.success();
    }

    /**
     * 用户ID登录
     * @param userId ID
     * @param userPassword 密码
     * @param session http会话
     * @return 是否成功登录的Response消息
     */
    @Transactional(rollbackFor = Exception.class)
    public ResponseResult<UserEntity> loginByUserId(String userId, String userPassword, HttpSession session) {
        UserEntity userEntity = userMapper.getUserInfoById(userId);
        return getUserEntityResponseResult(userPassword, session, userEntity);
    }

    /**
     * 用户邮箱登录
     * @param userMail 邮箱
     * @param userPassword 密码
     * @param session http会话
     * @return 是否成功登录的Response消息
     */
    @Transactional(rollbackFor = Exception.class)
    public ResponseResult<UserEntity> loginByMail(String userMail, String userPassword, HttpSession session) {
        UserEntity userEntity = userMapper.getUserInfoByMail(userMail);
        return getUserEntityResponseResult(userPassword, session, userEntity);
    }

    /**
     * 用户手机登录
     * @param userPhone 邮箱
     * @param userPassword 密码
     * @param session http会话
     * @return 是否成功登录的Response消息
     */
    @Transactional(rollbackFor = Exception.class)
    public ResponseResult<UserEntity> loginByPhone(String userPhone, String userPassword, HttpSession session) {
        UserEntity userEntity = userMapper.getUserInfoByPhone(userPhone);
        return getUserEntityResponseResult(userPassword, session, userEntity);
    }

    private ResponseResult<UserEntity> getUserEntityResponseResult(String userPassword, HttpSession session, UserEntity userEntity) {
        if (userEntity == null) {
            return ResultGenerator.error(400, "no account!");
        }
        if (!userEntity.getUserPassword().equals(userPassword)) {
            return ResultGenerator.error(400, "password wrong!");
        }
        session.setAttribute(IS_LOGIN, true);
        session.setAttribute("userName", userEntity.getUserName());
        session.setAttribute("userId", userEntity.getUserId());

        return ResultGenerator.success(userEntity);
    }

    /**
     * 用户注销
     * @param session http会话
     * @return 是否成功注销的Response消息
     */
    @Transactional(rollbackFor = Exception.class)
    public ResponseResult<Integer> logout(HttpSession session) {
        Boolean isUserLogin = (Boolean) session.getAttribute(IS_LOGIN);
        if (isUserLogin == null) {
            return ResultGenerator.error("illegal operation!");
        }
        session.setAttribute(IS_LOGIN, false);
        return ResultGenerator.success();
    }

    /**
     * 检查用户是否处于登录状态
     * @param session http会话
     * @return 用户是否处于登录状态的Response消息
     */
    @Transactional(rollbackFor = Exception.class)
    public ResponseResult<Integer> isLogin(HttpSession session) {
        if (session.getAttribute(IS_LOGIN) != null && (Boolean) session.getAttribute(IS_LOGIN)) {
            return ResultGenerator.success();
        } else {
            return ResultGenerator.error("not login!");
        }
    }

    /**
     * 返回所有特定用户角色的用户
     * @param userRole 用户角色
     * @return 所有这一用户角色的用户
     */
    public ResponseResult<ArrayList<UserEntity>> getUsersByRole(int userRole,HttpSession session){
        ArrayList<UserEntity> res=userMapper.getUsesByRole(userRole);
        return ResultGenerator.success(res);
    }

    public ResponseResult<Boolean> setUserRoleById(String userId,int userRole,HttpSession session){
        int res=userMapper.setUserRoleById(userId,userRole);
        return ResultGenerator.success();
    }

    public ResponseResult<UserEntity> getUserInfoById(String userId,HttpSession session){
        UserEntity entity=userMapper.getUserInfoById(userId);
        if(entity==null)
            return ResultGenerator.error("no such id");
        else
            return ResultGenerator.success(entity);
    }
}
