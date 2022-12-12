package com.example.server.controller;


import com.example.server.mapper.CommentsMapper;
import com.example.server.pojo.Comments;
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


}
