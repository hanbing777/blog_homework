package com.example.server.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.server.pojo.Logs;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author hanbing
 * @since 2022-12-09
 */
@Mapper
public interface LogsMapper extends BaseMapper<Logs> {

    Integer getCount();
}
