package com.shoukailiang.community;

import com.shoukailiang.community.oauth2.config.PasswordEncoderConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestPassword {
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Test
    public void test(){
        String pass = passwordEncoder.encode("123456");
        System.out.println(pass);
    }
}
