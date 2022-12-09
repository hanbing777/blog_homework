package com.example.server.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.server.mapper.MetasMapper;
import com.example.server.pojo.Metas;
import com.example.server.service.IMetasService;
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
public class MetasServiceImpl extends ServiceImpl<MetasMapper, Metas> implements IMetasService {

}
