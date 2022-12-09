package com.example.server.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.server.mapper.LogsMapper;
import com.example.server.pojo.Logs;
import com.example.server.service.ILogsService;
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
public class LogsServiceImpl extends ServiceImpl<LogsMapper, Logs> implements ILogsService {

}
