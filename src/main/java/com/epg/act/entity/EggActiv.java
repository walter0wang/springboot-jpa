package com.epg.act.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotNull;
import java.sql.Time;
import java.sql.Timestamp;

/**
 * @author WANGTAO WangTao
 * @since 2017/11/30 15:20
 */
@Data
@EqualsAndHashCode
public class EggActiv {
    private Integer id;
    @NotNull(message = "活动名称不可为空")
    private String name;
    @NotNull(message = "图片模板不可为空")
    private Integer picTplId;
    @NotNull(message = "奖品不可为空")
    private Integer prizeTplId;
    private Integer status;
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(dataType = "java.util.Date", example="2017-12-12")
    private Timestamp startDate;
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd 23:59:59")
    @ApiModelProperty(dataType = "java.util.Date", example="2017-12-30")
    private Timestamp endDate;
    private String weeks;
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd 00:00:00")
    @ApiModelProperty(dataType = "java.util.Date", example="20:12:30")
    private Time startTime;
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd 23:59:59")
    @ApiModelProperty(dataType = "java.util.Date", example="22:12:30")
    private Time endTime;
    @NotNull(message = "时点个数不可为空")
    @ApiModelProperty(value = "时间点个数")
    private Integer timeNum;
    @ApiModelProperty(value = "时间点最小间隔 单位：分")
    private Integer timeBetween;
    @ApiModelProperty(dataType = "java.util.Date", example="2017-12-25 16:11:25")
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private Timestamp createTime;
    @ApiModelProperty(required = true)
    private String createUser;
    @ApiModelProperty(dataType = "java.util.Date", example="2017-12-25 16:11:25")
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private Timestamp updateTime;

    @ApiModelProperty(required = true)
    private String updateUser;


    @TableField(exist = false)
    private Short[] channels;
}
