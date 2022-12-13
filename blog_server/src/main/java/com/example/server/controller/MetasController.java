package com.example.server.controller;


import com.example.server.mapper.*;
import com.example.server.pojo.Metas;
import com.example.server.pojo.RespBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author hanbing
 * @since 2022-12-09
 */
@RestController
@RequestMapping("admin/metas")
public class MetasController {
    @Autowired
    private MetasMapper metasMapper;
    @Autowired
    private ContentsMapper contentsMapper;
    @Autowired
    private CommentsMapper commentsMapper;
    @Autowired
    private AttachMapper attachMapper;
    @Autowired
    private LogsMapper logsMapper;
    @PostMapping("addMetas")
    public RespBean addMetas(@RequestBody Metas metas){
        if (metasMapper.insert(metas)>0) {
            return RespBean.success("添加成功");
        }else {
            return RespBean.error("添加失败");
        }
    }

    @GetMapping("deleteMetasById/{id}")
    public RespBean deleteMetasById(@PathVariable int id){
        if (metasMapper.deleteById(id)>0) {
            return RespBean.success("删除成功");
        }else {
            return RespBean.error("删除失败");
        }
    }

    @PostMapping("updateMetasById")
    public RespBean updateMetasById(@RequestBody Metas metas){
        if (metasMapper.updateById(metas)>0) {
            return RespBean.success("删除成功");
        }else {
            return RespBean.error("删除失败");
        }
    }

    @GetMapping("getMetasByType/{type}")
    public RespBean getMetasByType(@PathVariable String type){
        return RespBean.success("查询成功",metasMapper.getMetasByType(type));
    }


    @GetMapping("getLinkCount")
    public RespBean getLinkCount(){
        return RespBean.success("获取成功",metasMapper.getCount());
    }

    @GetMapping("getCount")
    public RespBean getCount(){
        HashMap<String, Integer> countMap = new HashMap<>();
        countMap.put("contentsCount",contentsMapper.getCount());
        countMap.put("fileCount",attachMapper.getCount());
        countMap.put("commentsCount",commentsMapper.getCount());
        countMap.put("linkCount",metasMapper.getCount());
        return RespBean.success("获取成功",countMap);
    }



}
