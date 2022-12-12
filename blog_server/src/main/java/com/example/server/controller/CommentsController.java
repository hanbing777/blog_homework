package com.example.server.controller;


import com.example.server.mapper.CommentsMapper;
import com.example.server.pojo.Comments;
import com.example.server.pojo.RespBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author hanbing
 * @since 2022-12-09
 */
@RestController
@RequestMapping("/comments")
public class CommentsController {
    @Autowired
    private CommentsMapper commentsMapper;


}
