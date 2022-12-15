package com.example.server.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.server.pojo.Attach;
import com.example.server.pojo.RespBean;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author hanbing
 * @since 2022-12-09
 */
public interface IAttachService extends IService<Attach> {

    void downloadFile(Integer id, HttpServletResponse response) throws IOException;

}
