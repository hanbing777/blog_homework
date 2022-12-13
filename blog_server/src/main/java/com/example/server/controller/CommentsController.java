package com.example.server.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.server.mapper.CommentsMapper;
import com.example.server.pojo.Comments;
import com.example.server.pojo.Contents;
import com.example.server.pojo.RespBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author hanbing
 * @since 2022-12-09
 */
@RestController
@RequestMapping("admin/comments")
public class CommentsController {
    @Autowired
    private CommentsMapper commentsMapper;

    @GetMapping("listAll")
    public RespBean listAll(){
        return RespBean.success("查询成功",commentsMapper.selectList(null));
    }

    @GetMapping("deleteById/{id}")
    public RespBean deleteById(@PathVariable int id){
        if (commentsMapper.deleteById(id)>0) {
            return RespBean.success("删除成功");
        }else {
            return RespBean.error("删除失败");
        }
    }



    @GetMapping("getCount")
    public RespBean getCount(){
        return RespBean.success("获取成功",commentsMapper.getCount());
    }

    @GetMapping("likeAndPage")
    public RespBean likeAndPage() {
        return RespBean.success("查询成功", commentsMapper.selectList(null));
    }


    @GetMapping("likeAndPage/{page}/{size}")
    public RespBean likeAndPage(@PathVariable("page") Integer page,
                                @PathVariable("size") Integer size) {
        Page<Comments> CommentsPage = new Page<>(page, size);
        QueryWrapper<Comments> commentsQueryWrapper = new QueryWrapper<>();
        return RespBean.success("查询成功", commentsMapper.selectPage(CommentsPage, commentsQueryWrapper));
    }

    @GetMapping("likeAndPage/{name}/{page}/{size}")
    public RespBean likeAndPage(@PathVariable("name") String title,
                                @PathVariable("page") Integer page,
                                @PathVariable("size") Integer size) {
        Page<Comments> commentsPage = new Page<>(page, size);
        QueryWrapper<Comments> commentsQueryWrapper = new QueryWrapper<>();
        commentsQueryWrapper.like("title", title);
        return RespBean.success("查询成功",  commentsMapper.selectPage(commentsPage, commentsQueryWrapper));
    }


}
