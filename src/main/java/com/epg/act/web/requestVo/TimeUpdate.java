package com.epg.act.web.requestVo;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.sql.Timestamp;

@EqualsAndHashCode(callSuper = true)
@Data
public class TimeUpdate extends TimeCreate{
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(dataType = "java.util.Date", example = "2017-12-12 20:00:00")
    private Timestamp datetime;
    private Integer picTplId;
}
