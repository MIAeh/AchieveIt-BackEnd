package com.achieveit.application.service;

import com.achieveit.application.entity.ClientInfo;
import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringRunner.class)
@SpringBootTest
@MapperScan("com.achieveit.application.mapper")
@Transactional
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ClientServiceTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(ClientServiceTest.class);

    @Autowired
    private ClientService clientService;

    @Test
    public void getClientInfoByIDTest() {
        String testID = "b5d51193-891c-45cc-91eb-1e449fbebbdd";
        ClientInfo clientInfo = clientService.getClientInfoByID(testID);
        ClientInfo expectedClientInfo = new ClientInfo("b5d51193-891c-45cc-91eb-1e449fbebbdd", "test_client", "test_company", "email@test.com", "12345678901", "test_address", 0);
        LOGGER.info(clientInfo.toString());
        LOGGER.info(expectedClientInfo.toString());
        Assert.assertEquals(clientInfo, expectedClientInfo);
    }

}
