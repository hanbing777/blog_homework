package com.example.server.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.server.Utils.FdfsUtils;
import com.example.server.mapper.AttachMapper;
import com.example.server.pojo.Attach;
import com.example.server.pojo.Comments;
import com.example.server.pojo.RespBean;
import com.example.server.service.IAttachService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
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
@RequestMapping("admin/attach")
public class AttachController {
    @Autowired
    private AttachMapper attachMapper;
    @Autowired
    private IAttachService attachService;
    @GetMapping("hello")
    public String hello(){
        return "hello";
    }

    @PostMapping("upFile")
    public RespBean upFile(@RequestParam MultipartFile file) {
        String name = file.getOriginalFilename();
        String type = name.substring(name.lastIndexOf(".") + 1);
        String url = FdfsUtils.URL + FdfsUtils.upload(file);
        Map<String, String> map = new HashMap<>();
        attachMapper.insert(new Attach(null,name,type,url,null,((int)(System.currentTimeMillis()/1000))));
        map.put("url", url);
        map.put("name", name);
        return RespBean.success("上传成功", map);
    }

    @GetMapping("downFile/{id}")
    public void downloadFile(@PathVariable("id") Integer id, HttpServletResponse response) throws Exception {
        attachService.downloadFile(id, response);
    }

    @GetMapping("listAll")
    public RespBean listAll(){
        return RespBean.success("查询成功",attachMapper.selectList(null));
    }

    @GetMapping("likeAndPage/{page}/{size}")
    public RespBean likeAndPage(@PathVariable("page") Integer page,
                                @PathVariable("size") Integer size) {
        Page<Attach> AttachPage = new Page<>(page, size);
        QueryWrapper<Attach> attachQueryWrapper = new QueryWrapper<>();
        return RespBean.success("查询成功", attachMapper.selectPage(AttachPage, attachQueryWrapper));
    }


}
