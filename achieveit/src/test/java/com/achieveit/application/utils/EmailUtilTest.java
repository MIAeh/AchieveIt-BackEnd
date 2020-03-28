package com.achieveit.application.utils;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class EmailUtilTest {

    @Autowired
    EmailUtil emailUtil;

    @Test
    public void sendTextEmail() {
        emailUtil.sendTextEmail("502892874@qq.com", "Email Test", "Test Content");
    }
}