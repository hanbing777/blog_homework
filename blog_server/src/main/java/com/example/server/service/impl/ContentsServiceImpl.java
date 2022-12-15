package com.example.server.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.server.mapper.ContentsMapper;
import com.example.server.pojo.Contents;
import com.example.server.pojo.RespBean;
import com.example.server.service.IContentsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author hanbing
 * @since 2022-12-09
 */
@Service
public class ContentsServiceImpl extends ServiceImpl<ContentsMapper, Contents> implements IContentsService {
    @Autowired
    private ContentsMapper contentsMapper;
    @Override
    public RespBean getGuiDang(String date) {
        return RespBean.success("查询成功",contentsMapper.getGuiDang(date));
    }
}
