package com.example.server.controller;


import com.example.server.mapper.CommentsMapper;
import com.example.server.mapper.ContentsMapper;
import com.example.server.pojo.Comments;
import com.example.server.pojo.Contents;
import com.example.server.pojo.RespBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author hanbing
 * @since 2022-12-09
 */
@RestController
@RequestMapping("admin/contents")
public class ContentsController {
    @Autowired
    private ContentsMapper contentsMapper;
    @Autowired
    private CommentsMapper commentsMapper;
    @PostMapping("addContents")
    public RespBean addContents(@RequestBody Contents contents){
        if (contentsMapper.insert(contents)>0) {
            return RespBean.success("成功");
        }else {
            return RespBean.error("失败");
        }
    }

    @GetMapping(value = {"article/{cid}"})
    public RespBean getArticle(@PathVariable Integer cid) {
        Contents contents = contentsMapper.selectById(cid);
        return RespBean.success("查看成功",contents);
    }



    @GetMapping("deleteContents/{cid}")
    public RespBean deleteContents(@PathVariable("cid") int cid){
        if (contentsMapper.deleteById(cid)>0) {
            return RespBean.success("删除成功");
        }else {
            return RespBean.error("删除失败");
        }
    }

    @PostMapping("updateContents")
    public RespBean updateContents(@RequestBody Contents contents){
        contents.setCreated((int)(System.currentTimeMillis()/1000));
        if (contentsMapper.updateById(contents)>0) {
            return RespBean.success("修改成功");
        }else {
            return RespBean.error("修改失败");
        }
    }

    @GetMapping("statusToPublish/{cid}")
    public RespBean statusToPublishByCid(@PathVariable int cid){
        if (contentsMapper.statusToPublishByCid(cid)>0) {
            return RespBean.success("修改成功");
        }else {
            return RespBean.error("修改失败");
        }
    }

    @GetMapping("statusToDraft/{cid}")
    public RespBean statusToDraftByCid(@PathVariable int cid){
        if (contentsMapper.statusToDraftByCid(cid)>0) {
            return RespBean.success("修改成功");
        }else {
            return RespBean.error("修改失败");
        }
    }

    @GetMapping("getCount")
    public RespBean getCount(){
        return RespBean.success("获取成功",contentsMapper.getCount());
    }




}
