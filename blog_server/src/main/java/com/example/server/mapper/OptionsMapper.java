package com.example.server.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.server.pojo.Options;
import com.example.server.pojo.RespBean;
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
public interface OptionsMapper extends BaseMapper<Options> {

    OptionsMapper getByName(String name);

    Integer updateByName(Options options);
}
