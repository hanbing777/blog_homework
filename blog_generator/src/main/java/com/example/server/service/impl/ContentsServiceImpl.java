package com.example.server.service.impl;

import com.example.server.pojo.Contents;
import com.example.server.mapper.ContentsMapper;
import com.example.server.service.IContentsService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author hanbing
 * @since 2022-12-09
 */
@Service
public class ContentsServiceImpl extends ServiceImpl<ContentsMapper, Contents> implements IContentsService {

}
