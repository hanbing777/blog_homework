package com.example.server.controller;


import com.example.server.mapper.UsersMapper;
import com.example.server.pojo.Bo.Password;
import com.example.server.pojo.Bo.UserNameAndEmail;
import com.example.server.pojo.RespBean;
import com.example.server.pojo.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author hanbing
 * @since 2022-12-09
 */
@RestController
@RequestMapping("admin/users")
public class UsersController {
    @Autowired
    private UsersMapper usersMapper;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("updateUserNameAndEmail")
    public RespBean updateUserNameAndEmail(@RequestBody UserNameAndEmail userNameAndEmail){
        if (usersMapper.updateUserNameAndEmail(userNameAndEmail)>0) {
            Map<String, Object> map = usersMapper.selectAllByUid(1);
            map.put("password","");
            return RespBean.success("修改成功",map);
        }else {
            return RespBean.error("修改失败");
        }
    }

    @PostMapping("updatePassword")
    public RespBean updatePassword(@RequestBody Password password){
        System.out.println(password.getPassword());
        if (usersMapper.updatePassword(passwordEncoder.encode(password.getPassword()))>0) {
            return RespBean.success("修改成功");
        }else {
            return RespBean.error("修改失败");
        }
    }

    @GetMapping("getUserInfo")
    public RespBean getUserInfo(){
        Map<String, Object> map = usersMapper.selectAllByUid(1);
        map.put("password","");
        return RespBean.success("获取成功",map);
    }
}
