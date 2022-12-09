package com.example.server.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @PROJECT_NAME: yeb-java
 * @DESCRIPTION: 公共返回类对象
 * @USER: 韩冰
 * @DATE: 2022/7/27 0027 18:10
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RespBean {
    private long code;
    private String messges;
    private Object obj;


    /**
     * 成功返回结果
     *
     * @param messges
     * @return
     */
    public static RespBean success(String messges) {
        return new RespBean(200, messges, null);
    }

    /**
     * 成功返回结果
     *
     * @param messges
     * @param obj
     * @return
     */
    public static RespBean success(String messges, Object obj) {
        return new RespBean(200, messges, obj);
    }

    /**
     * 失败返回结果
     *
     * @param messges
     * @return
     */
    public static RespBean error(String messges) {
        return new RespBean(500, messges, null);
    }

    /**
     * 失败返回结果
     *
     * @param messges
     * @param obj
     * @return
     */
    public static RespBean error(String messges, Object obj) {
        return new RespBean(500, messges, obj);
    }

}
