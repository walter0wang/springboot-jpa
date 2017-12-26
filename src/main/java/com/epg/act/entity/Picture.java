package com.epg.act.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.sql.Timestamp;

/**
 * @author WANGTAO
 * @since create 2017/11/30 15:20
 */
@Data
@EqualsAndHashCode
public class Picture {
    private Long id;
    private String uploadName;
    private String name;
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(dataType = "java.util.Date", example="2017-12-25 16:11:25")
    private Timestamp createTime;
    private String createUser;
}
