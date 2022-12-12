package com.example.server.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.server.pojo.RespBean;
import com.example.server.pojo.Users;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author hanbing
 * @since 2022-12-09
 */
public interface IUsersService extends IService<Users> {

    RespBean login(String username, String password, String code,String ip, HttpServletRequest request);
}
