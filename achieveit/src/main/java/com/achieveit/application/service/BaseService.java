package com.achieveit.application.service;

import com.achieveit.application.utils.BaseJson;
import com.achieveit.application.mapper.PinMapper;
import com.achieveit.application.mapper.PlanMapper;
import com.achieveit.application.mapper.UserInfoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BaseService {

    @Autowired
    private UserInfoMapper userInfoMapper;

    @Autowired
    private PlanMapper planMapper;

    @Autowired
    private PinMapper pinMapper;

    /**
     * 根据用户ID获取用户信息
     * errorCode - 0000(成功)/1001(未找到);
     * object - UserInfo;
     *
     * @return
     */
    public BaseJson getUserMsg(int id) {

        BaseJson baseJson = new BaseJson().setObject(userInfoMapper.getOne(id));

        if (baseJson.getObject() == null)
            return baseJson.setErrorCode("1001");

        return baseJson.setErrorCode("0000");
    }

    /**
     * 根据计划ID获取计划信息
     * errorCode - 0000(成功)/1001(未找到);
     * object - Plan;
     *
     * @param id
     * @return
     */
    public BaseJson getPlanMsg(int id) {

        BaseJson baseJson = new BaseJson().setObject(planMapper.getPlanByID(id));

        if (baseJson.getObject() == null)
            return baseJson.setErrorCode("1001");

        return baseJson.setErrorCode("0000");
    }

    /**
     * 根据打点ID获取计划信息
     * errorCode - 0000(成功)/1001(未找到);
     * object - Pin;
     *
     * @param id
     * @return
     */
    public BaseJson getPinMsg(int id) {

        BaseJson baseJson = new BaseJson().setObject(pinMapper.getPinByID(id));

        if (baseJson.getObject() == null)
            return baseJson.setErrorCode("1001");

        return baseJson.setErrorCode("0000");
    }

}
