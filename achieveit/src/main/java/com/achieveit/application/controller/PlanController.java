package com.achieveit.application.controller;


import com.achieveit.application.domain.Result;
import com.achieveit.application.enums.PlanType;
import com.achieveit.application.utils.ResultUtil;
import com.achieveit.application.annotation.Logged;
import com.achieveit.application.domain.Token;
import com.achieveit.application.entity.MyUserInfo;
import com.achieveit.application.entity.Plan;
import com.achieveit.application.entity.Route;
import com.tripin.application.exception.TripinException;
import com.achieveit.application.mapper.PlanMapper;
import com.achieveit.application.service.PlanService;
import com.tripin.application.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/plan")
public class PlanController {
    private final PlanService planService;

    //测试
    private final PlanMapper planMapper;


    @Autowired
    public PlanController(PlanService planService, PlanMapper planMapper) {
        this.planService = planService;
        this.planMapper = planMapper;
    }

    @RequestMapping
    public Plan getPlan(@RequestParam("id") Integer id) throws TripinException {
        Plan plan= planService.getPlanById( id );
        return plan;
    }

    @RequestMapping("/routers")
    public List<Route> getRoutes(@RequestParam("id") Integer id) throws TripinException {
        List routes= planService.getRouterByID( id );
        //routes 可能不存在
        return routes;
    }

    @RequestMapping("/insert")
    public int insertPlan(@RequestParam("mapID") Integer mapID, @RequestParam("planName") String planName) throws TripinException {

        Plan plan = new Plan();
        plan.setMapID( mapID );
        plan.setPlanName( planName );
        plan.setPlanType( PlanType.PUBLIC );
        plan.setPlanCreatedDate( new Date( 2010, 5, 16 ) );
        plan.setPlanModifiedDate( new Date( 2010, 5, 16 ) );

        planMapper.insert( plan);
        return 0;
    }



    //==================
    UserInfoService userInfoService = null;

    /**
     * @return 封装了token的result
     * @throws TripinException
     * @Description: 使用userName、phone、email和password进行登陆
     */
    @Logged({"account", "password"})
    @PostMapping("/login")
    public Result userLogin(@RequestParam("account") String account, @RequestParam("password") String password) throws TripinException {
        // 将获取到的account和password给Service进行验证，将token返回给用户
        Token token = userInfoService.userLogin( account, password );
        return ResultUtil.success( token );
    }

    /**
     * @return null
     * @throws TripinException
     * @Description: 用户提供Token进行注销
     */
    @Logged({"tokenString", "userID"})
    @PostMapping("/logout")
    public Result userLogout(@RequestParam("tokenString") String tokenString, @RequestParam("userID") int userID) {
        Token token = new Token( tokenString, userID );
        userInfoService.userLogout( token );
        return ResultUtil.success();
    }

    /**
     * @return null
     * @throws TripinException
     * @Description: 用户提供Token获取其用户信息
     */
    @Logged({"tokenString", "userID"})
    @GetMapping("/info")
    public Result getMyUserInfo(@RequestParam("tokenString") String tokenString, @RequestParam("userID") int userID) {
        Token token = new Token( tokenString, userID );
        MyUserInfo myUserInfo = userInfoService.getMyUserInfo( token );
        return ResultUtil.success( myUserInfo );
    }

    /**
     * @return null
     * @throws TripinException
     * @Description: 用户提供Token获取修改其密码
     */
    @Logged({"tokenString", "userID", "newPassword"})
    @PostMapping("/set/password")
    public Result updateUserPassword(@RequestParam("tokenString") String tokenString, @RequestParam("userID") int userID,
                                     @RequestParam("newPassword") String newPassword) {
        Token token = new Token( tokenString, userID );
        userInfoService.updateUserPassword( token, newPassword );
        return ResultUtil.success();
    }

//    ---- 下面是测试函数 ----

    @GetMapping("all")
    public Result testGetAllUser() {
        return userInfoService.getAllUsers();
    }

}
