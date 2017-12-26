package com.epg.act.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotNull;
import java.sql.Timestamp;

/**
 * @author WANGTAO WangTao
 * @since 2017/11/30 15:20
 */
@Data
@EqualsAndHashCode
public class EggPicTpl {
    private Integer id;
    @NotNull(message = "名称不可为空")
    private String name;
    @ApiModelProperty(example = "1", notes = "模板类型 （1:标清 2:高清）")
    @NotNull(message = "必填")
    private Integer type;
    private Integer num;
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private Timestamp createTime;
    private String createUser;
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private Timestamp updateTime;
    private String updateUser;

    @TableField(exist = false)
    private String activName;
    @TableField(exist = false)
    private String activStatus;

}
