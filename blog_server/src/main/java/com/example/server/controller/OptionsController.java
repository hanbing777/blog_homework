package com.example.server.controller;


import com.example.server.mapper.OptionsMapper;
import com.example.server.pojo.Bo.ListOptions;
import com.example.server.pojo.Options;
import com.example.server.pojo.RespBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.swing.*;
import java.util.List;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author hanbing
 * @since 2022-12-09
 */
@RestController
@RequestMapping("admin/options")
public class OptionsController {
    @Autowired
    private OptionsMapper optionsMapper;

    @GetMapping("getByName/{name}")
    public RespBean getByName(@PathVariable String name) {
        return RespBean.success("查询成功", optionsMapper.getByName(name));
    }

    @GetMapping("getAll")
    public RespBean getAll() {
        return RespBean.success("查询成功", optionsMapper.selectList(null));
    }

    @PostMapping("updateByName")
    public RespBean updateByName(@RequestBody Options options) {
        if (optionsMapper.updateByName(options) > 0) {
            return RespBean.success("修改成功");
        } else {
            return RespBean.error("修改成功");
        }
    }

    @PostMapping("updateByNames")
    public RespBean updateByNames(@RequestBody ListOptions listOptions) {
        Integer i = 0;
        for (Options option : listOptions.getOptions()) {
            i = i + optionsMapper.updateByName(option);
        }

        if (i==listOptions.getOptions().size()) {
            return RespBean.success("修改成功");
        } else {
            return RespBean.error("修改成功");
        }
    }

}
