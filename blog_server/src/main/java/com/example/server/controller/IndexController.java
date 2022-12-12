package com.example.server.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.server.mapper.CommentsMapper;
import com.example.server.mapper.ContentsMapper;
import com.example.server.pojo.Comments;
import com.example.server.pojo.Contents;
import com.example.server.pojo.RespBean;
import com.example.server.service.IContentsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class IndexController {

    @Autowired
    private IContentsService contentService;
    @Autowired
    private ContentsMapper contentsMapper;
    @Autowired
    private CommentsMapper commentsMapper;

    @GetMapping("hello")
    public String hello(){
        return "hello";
    }

    @GetMapping(value = {"article/{cid}"})
    public RespBean getArticle(@PathVariable Integer cid) {
        Contents contents = contentsMapper.selectById(cid);
        List<Comments> comments = commentsMapper.selectByCid(cid);
        Map<String, Object> mapObj = new HashMap<>();
        mapObj.put("contents",contents);
        mapObj.put("comments",comments);
        if (null == contents || "draft".equals(contents.getStatus())) {
            return RespBean.error("暂未发布该文章");
        }
        HashMap<String, Integer> hitsMap = new HashMap<>();
        hitsMap.put("hits",contents.getHits()+1);
        hitsMap.put("cid",cid);
        commentsMapper.updateHitsByCid(hitsMap);
        return RespBean.success("查看成功",mapObj);
    }

    @GetMapping("likeAndPage")
    public RespBean likeAndPage() {
        return RespBean.success("查询成功", contentsMapper.selectList(null));
    }


    @GetMapping("likeAndPage/{page}/{size}")
    public RespBean likeAndPage(@PathVariable("page") Integer page,
                         @PathVariable("size") Integer size) {
        Page<Contents> booksPage = new Page<>(page, size);
        return RespBean.success("查询成功", contentsMapper.selectPage(booksPage, null));
    }

    @GetMapping("likeAndPage/{name}/{page}/{size}")
    public RespBean likeAndPage(@PathVariable("name") String name,
                                @PathVariable("page") Integer page,
                                @PathVariable("size") Integer size) {
        Page<Contents> booksPage = new Page<>(page, size);
        QueryWrapper<Contents> booksQueryWrapper = new QueryWrapper<>();
        booksQueryWrapper.like("name", name);
        contentsMapper.selectPage(booksPage, booksQueryWrapper);
        return RespBean.success("查询成功", contentsMapper.selectPage(booksPage, booksQueryWrapper));
    }

    @PostMapping("addComments")
    public RespBean addComments(Comments comments){
        if (commentsMapper.insert(comments)>0) {
            return RespBean.success("评论成功");
        }else {
            return RespBean.error("评论失败");
        }
    }


}
