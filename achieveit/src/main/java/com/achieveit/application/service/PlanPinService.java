package com.achieveit.application.service;

import com.achieveit.application.utils.BaseJson;
import com.achieveit.application.entity.Participants;
import com.achieveit.application.entity.Pin;
import com.achieveit.application.entity.Plan;
import com.achieveit.application.entity.Plat;
import com.achieveit.application.mapper.ParticipantMapper;
import com.achieveit.application.mapper.PinMapper;
import com.achieveit.application.mapper.PlanMapper;
import com.achieveit.application.mapper.PlatMapper;
import org.apache.commons.collections15.CollectionUtils;
import org.apache.commons.collections15.Transformer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class PlanPinService extends BaseService {

    private static final Logger LOGGER = LoggerFactory.getLogger(PlanPinService.class);

    @Autowired
    private PlanMapper planMapper;

    @Autowired
    private ParticipantMapper participantsMapper;

    @Autowired
    private PlatMapper platMapper;

    @Autowired
    private PinMapper pinMapper;


    /**
     * 根据用户找出所有使用中计划
     * errorCode - 0000(成功)/1001(未找到);
     * object - List - Plan - plans;
     *
     * @param userId
     * @return
     */
    public BaseJson getPlansByUserId(Integer userId) {

        BaseJson baseJson = new BaseJson();

        if (getUserMsg(userId).getErrorCode().equals("1001"))
            return baseJson;

        Participants participants = new Participants();
        participants.setParticipantID(userId);

        baseJson.setObject(participantsMapper.getPlanIDByuserID(participants.getParticipantID()));
        List<Participants> participantsList = (List<Participants>) baseJson.getObject();

        if (participantsList == null || (participantsList.isEmpty()))
            return baseJson.setObject(new ArrayList<Plan>()).setErrorCode("0000");

        List<Integer> planIds = (List<Integer>) CollectionUtils.collect(
                participantsList, new Transformer() {
                    public Object transform(Object object) {
                        Integer planId = null;
                        try {
                            planId = ((Participants) object).getPlanID();
                        } catch (Exception e) {
                            LOGGER.error("planIds build error", e);
                        }
                        return planId;
                    }
                });
        List<Plan> plans =  new ArrayList<>();
        for(int planId :planIds) {
             plans.add(planMapper.getByPlanId(planId));
        }
        return baseJson.setObject(plans).setErrorCode("0000");
    }

    /**
     * 根据用户添加计划与地图(只添加计划，不打点)
     * errorCode - 0000(成功)/1001(未找到);
     * object - null;
     *
     * @param userId
     * @return
     */
    public BaseJson addPlanByUserId(Integer userId, Plan plan, Plat plat) {

        BaseJson baseJson = new BaseJson();
        if (getUserMsg(userId).getErrorCode().equals("1001"))
            return baseJson;

        planMapper.insert(plan);

        platMapper.insert(plat);

        return baseJson.setErrorCode("0000");
    }

    /**
     * 根据用户修改计划与地图(只添加计划，不打点)
     * errorCode - 0000(成功)/1001(未找到);
     * object - null;
     *
     * @param userId
     * @return
     */
    public BaseJson updatePlanByUserId(Integer userId, Plan plan, Plat plat) {

        BaseJson baseJson = new BaseJson();
        if (getUserMsg(userId).getErrorCode().equals("1001"))
            return baseJson;

        planMapper.update(plan);

        platMapper.update(plat);

        return baseJson.setErrorCode("0000");
    }

    /**
     * 根据计划批量添加打点
     * errorCode - 0000(成功)/1001(未找到);
     * object - null;
     *
     * @param planId
     * @param pinList
     * @return
     */
    public BaseJson addPinByUserId(Integer planId, List<Pin> pinList) {

        BaseJson baseJson = new BaseJson();
        if (getPlanMsg(planId).getErrorCode().equals("1001"))
            return baseJson;

        for (Pin pin : pinList) {
            pinMapper.insert(pin);
        }

        return baseJson.setErrorCode("0000");
    }

    /**
     * 根据打点Id修改信息
     * errorCode - 0000(成功)/1001(未找到);
     * object - null;
     *
     * @param pinId
     * @param pin
     * @return
     */
    public BaseJson updatePinByPlanId(Integer pinId, Pin pin) {

        BaseJson baseJson = new BaseJson();
        if (getPinMsg(pinId).getErrorCode().equals("1001"))
            return baseJson;

        pinMapper.updateBypinId(pin);

        return baseJson.setErrorCode("0000");
    }

    /**
     * 根据计划id找出所有属于这个计划在使用的打点
     * errorCode - 0000(成功)/1001(未找到);
     * object - List - Pin - pins;
     *
     * @param planId
     * @return
     */
    public BaseJson getPinsByPlanId(Integer planId) {

        BaseJson baseJson = new BaseJson();
        if (getUserMsg(planId).getErrorCode().equals("1001"))
            return baseJson;

        Pin pin = new Pin();
        pin.setPlanID(planId);

        List<Pin> pins = pinMapper.getByPlanId(pin);
        return baseJson.setObject(pins).setErrorCode("0000");
    }

}
