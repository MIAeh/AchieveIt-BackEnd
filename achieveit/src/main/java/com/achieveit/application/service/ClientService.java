package com.achieveit.application.service;

import com.achieveit.application.annotation.Logged;
import com.achieveit.application.entity.ClientInfo;
import com.achieveit.application.mapper.ClientMapper;
import org.springframework.stereotype.Service;

/**
 * Client Service
 */
@Service
public class ClientService {

    private final ClientMapper clientMapper;

    public ClientService(ClientMapper clientMapper) {
        this.clientMapper = clientMapper;
    }

    /**
     * 通过客户ID获取客户信息
     *
     * @param clientID 客户ID
     * @return 客户信息
     */
    @Logged({"clientID"})
    public ClientInfo getClientInfoByID(String clientID) {
        ClientInfo clientInfo = clientMapper.getClientInfoByID(clientID);
        return clientInfo;
    }
}
