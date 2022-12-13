package com.example;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

@RunWith(SpringRunner.class)
@SpringBootTest
public class Test01 {
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Test
    public void test01(){
        System.out.println(((int)(System.currentTimeMillis()/1000)));
    }

    @Test
    public void test02(){
        String encode = passwordEncoder.encode("123");
        System.out.println(encode);
        System.out.println(passwordEncoder.matches("123","$2a$10$l9maqDrqB/LGTrdqqkruB.g1CDJp7IJRzZm1rjUVeSY8PH0wIAAMO"));
    }
}
