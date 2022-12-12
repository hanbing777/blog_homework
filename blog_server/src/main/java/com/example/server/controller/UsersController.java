package com.example.server.controller;


import com.example.server.mapper.UsersMapper;
import com.example.server.pojo.Bo.UserNameAndEmail;
import com.example.server.pojo.RespBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
            return RespBean.success("修改成功");
        }else {
            return RespBean.error("修改失败");
        }
    }

    @PostMapping("updatePassword")
    public RespBean updatePassword(@RequestBody String password){
        if (usersMapper.updatePassword(passwordEncoder.encode(password))>0) {
            return RespBean.success("修改成功");
        }else {
            return RespBean.error("修改失败");
        }
    }
}
