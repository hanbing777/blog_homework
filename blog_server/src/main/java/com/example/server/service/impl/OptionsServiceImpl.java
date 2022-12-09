package com.example.server.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.server.mapper.OptionsMapper;
import com.example.server.pojo.Options;
import com.example.server.service.IOptionsService;
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
public class OptionsServiceImpl extends ServiceImpl<OptionsMapper, Options> implements IOptionsService {

}
