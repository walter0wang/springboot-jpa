package com.epg.act.web.requestVo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class TimeCreate {
    @NotNull
    private Integer activId;
    @NotNull(message = "操作用户不可为空")
    @ApiModelProperty(value = "更新用户", required = true)
    private String updateUser;
}
