package com.example.server.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.server.pojo.Metas;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author hanbing
 * @since 2022-12-09
 */
@Mapper
public interface MetasMapper extends BaseMapper<Metas> {

    List<Metas> getMetasByType(String type);
}
