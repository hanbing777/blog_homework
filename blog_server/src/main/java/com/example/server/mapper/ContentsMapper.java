package com.example.server.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.server.pojo.Contents;
import org.apache.ibatis.annotations.MapKey;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author hanbing
 * @since 2022-12-09
 */
@Mapper
public interface ContentsMapper extends BaseMapper<Contents> {

    int statusToPublishByCid(int cid);
    int statusToDraftByCid(int cid);

    int getCount();

    List<String> getDate();

    @MapKey("cid")
    List<Map<String,Object>> getGuiDang(String date);
}
