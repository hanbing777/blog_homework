package com.example;

import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;

@SpringBootTest
public class Test01 {
    @Test
    public void test01(){
        System.out.println(((int)(System.currentTimeMillis()/1000)));
    }
}
