package com.example.server.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.server.pojo.Contents;
import com.example.server.pojo.RespBean;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author hanbing
 * @since 2022-12-09
 */
public interface IContentsService extends IService<Contents> {

    RespBean getGuiDang(String date);
}
