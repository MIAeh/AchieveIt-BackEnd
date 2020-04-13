package com.achieveit.application.schedule;

import com.achieveit.application.entity.RiskEntity;
import com.achieveit.application.entity.UserEntity;
import com.achieveit.application.mapper.RiskMapper;
import com.achieveit.application.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

@Component
public class MailScheduleTask {
    private static final SimpleDateFormat dataFormat = new SimpleDateFormat("HH:mm:ss");
    private final RiskMapper riskMapper;
    private final UserMapper userMapper;

    @Autowired
    public MailScheduleTask(RiskMapper riskMapper, UserMapper userMapper) {
        this.riskMapper = riskMapper;
        this.userMapper = userMapper;
    }

    private ArrayList<SendInfo> getRiskSendList(int riskID) {
        ArrayList<SendInfo> sendList = new ArrayList<SendInfo>();
        ArrayList<String> riskHolders = riskMapper.getAllRiskHolderByRiskId(riskID);
        ArrayList<String> riskHoldersMail = new ArrayList<String>();
        for (String holder : riskHolders) {
            UserEntity userEntity = userMapper.getUserInfoById(holder);
            if (userEntity == null) continue;
            String userMail = userEntity.getUserMail();
            if (userMail == null || userMail.equals("")) continue;
            riskHoldersMail.add(userMail);
        }
        for (String userMail : riskHoldersMail) {
            String userContent = "注意项目风险";
            SendInfo sendInfo = new SendInfo(userMail, userContent);
            sendList.add(sendInfo);
        }
        return sendList;
    }

    private ArrayList<SendInfo> getSendList() {
        ArrayList<SendInfo> sendList = new ArrayList<SendInfo>();
        ArrayList<RiskEntity> riskEntities = riskMapper.getAllRisks();

        for (RiskEntity riskEntity : riskEntities) {
            int riskFrequency = riskEntity.getRiskFrequency();
            if (riskFrequency <= 0) continue;
            Date lastSendTime = riskMapper.getRiskLastSendTimeFromRiskId(riskEntity.getRiskID());
            Date currDate = new Date(System.currentTimeMillis());
            if (lastSendTime == null) {
                riskMapper.updateRiskLastSendTime(riskEntity.getRiskID(), currDate);
                return getRiskSendList(riskEntity.getRiskID());
            } else {
                long pastTime = currDate.getTime() - lastSendTime.getTime();
                if (pastTime >= riskEntity.getRiskFrequency() * 3600 * 24 * 1000) {
                    riskMapper.updateRiskLastSendTime(riskEntity.getRiskID(), currDate);
                    return getRiskSendList(riskEntity.getRiskID());
                }
            }
        }

        return sendList;
    }

    @Scheduled(fixedRate = 1000 * 3600 * 12)
    public void checkMailAndSend() {
        //return;
        ArrayList<SendInfo> sendList = getSendList();
        for (SendInfo sendInfo : sendList) {
            //sendMail
        }
    }
}

class SendInfo {
    private String sendAddress;
    private String sendContent;

    public SendInfo() {
    }

    public SendInfo(String sendAddress, String sendContent) {
        this.sendAddress = sendAddress;
        this.sendContent = sendContent;
    }

    public String getSendAddress() {
        return sendAddress;
    }

    public void setSendAddress(String sendAddress) {
        this.sendAddress = sendAddress;
    }

    public String getSendContent() {
        return sendContent;
    }

    public void setSendContent(String sendContent) {
        this.sendContent = sendContent;
    }
}
