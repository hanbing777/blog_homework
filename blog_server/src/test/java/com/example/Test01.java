package com.example;

import com.example.server.mapper.ContentsMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
public class Test01 {
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private ContentsMapper contentsMapper;

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
    @Test
    public void test03(){
        List<String> date = contentsMapper.getDate();
        System.out.println(date.toString());
    }
    @Test
    public void test04(){
        List<String> dates = contentsMapper.getDate();
        HashMap<String, Object> mapAll = new HashMap<>();
        for (String date : dates) {
            List<Map<String, Object>> guiDang = contentsMapper.getGuiDang(date);
            mapAll.put(date,guiDang);
        }
        System.out.println(mapAll);
    }
}
