package com.example.server.pojo;

import com.baomidou.mybatisplus.annotation.*;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
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
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("t_contents")
@ApiModel(value="Contents对象", description="")
public class Contents implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "cid", type = IdType.AUTO)
    private Integer cid;

    private String title;

    private String slug;

    private Integer created;

    private Integer modified;

    @ApiModelProperty(value = "内容文字")
    private String content;

    @TableField("author_id")
    private Integer authorId;

    private String type;

    private String status;

    private String tags;

    private String categories;

    private Integer hits;

    @TableField("comments_num")
    private Integer commentsNum;

    @TableField("allow_comment")
    private Boolean allowComment;

    @TableField("allow_ping")
    private Boolean allowPing;

    @TableField("allow_feed")
    private Boolean allowFeed;

    @TableLogic
    private Integer deleted;

}
