package com.epg.act.web.requestVo;

import com.epg.act.entity.EggActiv;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.sql.Timestamp;

@EqualsAndHashCode(callSuper = true)
@Data
public class ActivCreate extends EggActiv{
    @ApiModelProperty(hidden = true)
    private Integer id;
    /*@NotNull(message = "活动名称不可为空")
    private String name;
    @NotNull(message = "图片模板不可为空")
    private Integer picTplId;
    @NotNull(message = "奖品不可为空")
    private Integer prizeTplId;
    private String weeks;
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd 00:00:00")
    @ApiModelProperty(dataType = "java.util.Date", example="20:12:30", required = true)
    private Time startTime;
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd 23:59:59")
    @ApiModelProperty(dataType = "java.util.Date", example="22:12:30", required = true)
    private Time endTime;
    @NotNull(message = "时点个数不可为空")
    private Integer timeNum;
    private Integer timeBetween;
    @NotNull(message = "need create user")
    @ApiModelProperty(required = true)
    private String createUser;*/
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd")
    @ApiModelProperty(dataType = "java.util.Date", example="2017-12-12", required = true)
    private Timestamp startDate;
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd")
    @ApiModelProperty(dataType = "java.util.Date", example="2017-12-30", required = true)
    private Timestamp endDate;
    @ApiModelProperty(hidden = true)
    private Timestamp createTime;
    @ApiModelProperty(hidden = true)
    private Timestamp updateTime;
    @ApiModelProperty(hidden = true)
    private String updateUser;
    @ApiModelProperty(hidden = true)
    private Integer status;
}
