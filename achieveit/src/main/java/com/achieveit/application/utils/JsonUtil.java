package com.achieveit.application.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class JsonUtil {

    private final ObjectMapper objectMapper;

    private static final Logger logger = LoggerFactory.getLogger("com.service.UserService");

    @Autowired
    public JsonUtil(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    public String writeJson(Object object) throws JsonProcessingException {
        logger.info("writeJson() param:" + object.toString());
        return objectMapper.writeValueAsString(object);
    }

    public Object readJson(String json, Class objectClass) throws IOException {
        logger.info("readJson() param:" + json + objectClass.toString());
        return objectMapper.readValue(json, objectClass);
    }
}
