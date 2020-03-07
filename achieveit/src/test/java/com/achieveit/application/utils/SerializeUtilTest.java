package com.achieveit.application.utils;

import com.achieveit.application.entity.ClientInfo;
import com.achieveit.application.service.ProjectServiceTest;
import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

import static org.junit.Assert.*;

public class SerializeUtilTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(SerializeUtilTest.class);

    @Test
    public void serializeTest() throws IOException, ClassNotFoundException {
        ClientInfo clientInfo = new ClientInfo("b5d51193-891c-45cc-91eb-1e449fbebbdd", "test_client", "test_company", "email@test.com", "12345678901", "test_address", 0);
        String serializedClientInfo = SerializeUtil.serialize(clientInfo);
        LOGGER.info(serializedClientInfo);
        Assert.assertNotNull(serializedClientInfo);
        ClientInfo unserializedClientInfo = (ClientInfo)SerializeUtil.unserialize(serializedClientInfo);
        Assert.assertEquals(clientInfo, unserializedClientInfo);
    }

}