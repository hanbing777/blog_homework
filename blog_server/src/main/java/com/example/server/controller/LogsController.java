package com.example.server.controller;


import com.example.server.mapper.LogsMapper;
import com.example.server.pojo.RespBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.GetMapping;
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
@RequestMapping("/logs")
public class LogsController {
    @Autowired
    private LogsMapper logsMapper;
    @GetMapping("GetLogs")
    public RespBean GetLogs(){
        return RespBean.success("获取成功",logsMapper.selectList(null));
    }
}
