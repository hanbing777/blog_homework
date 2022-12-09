package com.example.server.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author hanbing
 * @since 2022-12-09
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("t_comments")
@ApiModel(value="Comments对象", description="")
public class Comments implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "coid", type = IdType.AUTO)
    private Integer coid;

    private Integer cid;

    private Integer created;

    private String author;

    @TableField("author_id")
    private Integer authorId;

    @TableField("owner_id")
    private Integer ownerId;

    private String mail;

    private String url;

    private String ip;

    private String agent;

    private String content;

    private String type;

    private String status;

    private Integer parent;


}
