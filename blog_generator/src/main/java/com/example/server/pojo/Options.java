package com.example.server.pojo;

import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

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
@TableName("t_options")
@ApiModel(value="Options对象", description="")
public class Options implements Serializable {

    private static final long serialVersionUID = 1L;

    private String name;

    private String value;

    private String description;


}
