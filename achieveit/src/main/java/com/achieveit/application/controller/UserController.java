package com.achieveit.application.controller;
import com.achieveit.application.annotation.Logged;
import com.achieveit.application.domain.Result;
import com.achieveit.application.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Title:  UserController
 * Description: 接收请求并处理 用户
 *
 **/
@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    /**
     *
     * @param userName
     * @param password
     * @return Result
     * {
     *   "errorCode": 0,
     *   "token": "xxxxxxxxx"
     * }
     */
    @Logged({"userID", "password"})
    @PostMapping("/loginByID")
    public Result userLoginByID(@RequestParam("userID") String userName, @RequestParam("password") String password) {
        return userService.userLoginByID();
    }

    //验证码
    @GetMapping("/register/code")
    public BaseJson getVerifyCode(@RequestParam("phone") String phone) {
        return userService.validatePhone( phone );
    }

    //用户登录
    @Logged({"account", "password"})
    @PostMapping("/login")
    public BaseJson userLogin(@RequestParam("account") String account, @RequestParam("password") String password) {
        return userService.userLogin( account, password );
    }

    @Logged({"userID", "newPassword", "oldPassword"})
    @PostMapping("/update/password")
    public BaseJson updateUserPassword(@RequestParam("id") int userID,
                                       @RequestParam("oldPassword") String oldPassword,
                                       @RequestParam("newPassword") String newPassword) {
        return userService.updateUserPassword( userID, oldPassword, newPassword );
    }
    //更新密码
    @PostMapping("/updata/email")
    public BaseJson updateUserPassword(@RequestParam("id") int userId, @RequestParam("email") String email) {
        return userService.setUserEmail( userId, email );
    }

}