package com.example.server.pojo.Bo;

import com.example.server.pojo.Options;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.util.ArrayList;

/**
 * @PROJECT_NAME: blog_homework
 * @DESCRIPTION:
 * @USER: 韩冰
 * @DATE: 2022/12/12 0012 19:23
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ListOptions {
    private ArrayList<Options> options;
}
