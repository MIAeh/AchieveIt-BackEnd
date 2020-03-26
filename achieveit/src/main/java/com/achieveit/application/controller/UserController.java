package com.achieveit.application.controller;
import com.achieveit.application.entity.UserEntity;
import com.achieveit.application.service.UserService;
import com.achieveit.application.wrapper.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;

/**
 * Controller for User
 *
 * @author Alevery, Felix
 */
@RestController
@RequestMapping("/user/")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    /**
     * 用户注册
     * @param userMail 邮箱
     * @param userName 用户名
     * @param userPassword 密码
     * @param session http会话
     * @return 是否成功注册的Response消息
     */
    @CrossOrigin
    @PostMapping("register")
    public ResponseResult<Integer> register(@RequestParam(name = "usermail") String userMail, @RequestParam(name = "username") String userName,
                                            @RequestParam(name = "userpassword") String userPassword, HttpSession session) {
        return this.userService.register(userMail, userName, userPassword);
    }

    /**
     * 用户登录
     * @param userId ID
     * @param userPassword 密码
     * @param session http会话
     * @return 是否成功登录的Response消息
     */
    @PostMapping("userLoginById")
    public ResponseResult<UserEntity> userLoginById(@RequestParam(name = "userid") String userId,
                                                    @RequestParam(name = "userpassword") String userPassword, HttpSession session) {
        return this.userService.loginByUserId(userId, userPassword, session);
    }

    /**
     * 用户登录
     * @param userMail 邮箱
     * @param userPassword 密码
     * @param session http会话
     * @return 是否成功登录的Response消息
     */
    @CrossOrigin
    @PostMapping("userLoginByMail")
    public ResponseResult<UserEntity> userLoginByMail(@RequestParam(name = "usermail") String userMail,
                                            @RequestParam(name = "userpassword") String userPassword, HttpSession session) {
        return this.userService.loginByMail(userMail, userPassword, session);
    }

    /**
     * 用户登录
     * @param userPhone 手机
     * @param userPassword 密码
     * @param session http会话
     * @return 是否成功登录的Response消息
     */
    @CrossOrigin
    @PostMapping("userLoginByPhone")
    public ResponseResult<UserEntity> userLoginByPhone(@RequestParam(name = "userphone") String userPhone,
                                            @RequestParam(name = "userpassword") String userPassword, HttpSession session) {
        return this.userService.loginByPhone(userPhone, userPassword, session);
    }

    /**
     * 用户注销
     * @param session http会话
     * @return 是否成功注销的Response消息
     */
    @CrossOrigin
    @PostMapping("logout")
    public ResponseResult<Integer> logout(HttpSession session) {
        return this.userService.logout(session);
    }

    /**
     * 检查用户是否处于登录状态
     * @param session http会话
     * @return 用户是否处于登录状态的Response消息
     */
    @CrossOrigin
    @GetMapping("isLogin")
    public ResponseResult<Integer> isLogin(HttpSession session) {
        return this.userService.isLogin(session);
    }

    /**
     * 返回所有特定用户角色的用户
     * @param userRole 用户角色
     * @return 所有这一用户角色的用户
     */
    @CrossOrigin
    @GetMapping("getUsersByRole")
    public ResponseResult<ArrayList<UserEntity>> getUsersByRole(@RequestParam(name = "userrole") int userRole,HttpSession session){
        return userService.getUsersByRole(userRole,session);
    }

    @CrossOrigin
    @GetMapping("setUserRoleById")
    public ResponseResult<Boolean> setUserRoleById(@RequestParam(name = "userid")String userId,@RequestParam(name = "userrole") int userRole,HttpSession session) {
        return userService.setUserRoleById(userId,userRole, session);
    }

    @CrossOrigin
    @GetMapping("getUserInfoById")
    public ResponseResult<UserEntity> getUserInfoById(@RequestParam(name = "userid")String userId,HttpSession session) {
        return userService.getUserInfoById(userId,session);
    }

    @CrossOrigin
    @GetMapping("getAllUserInfo")
    public ResponseResult<ArrayList<UserEntity>> getAllUserInfo(HttpSession session){
        return userService.getAllUserInfo();
    }
}