package com.example.server.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.server.pojo.Comments;
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
public interface CommentsMapper extends BaseMapper<Comments> {

    List<Comments> selectByCid(Integer cid);

    Integer updateHitsByCid(Map<String,Integer> map);

    int getCount();

    List<Comments> getCommentsByCid(int cid);
}
