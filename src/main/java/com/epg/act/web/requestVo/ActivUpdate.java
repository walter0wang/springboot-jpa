package com.epg.act.web.requestVo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotNull;

@EqualsAndHashCode(callSuper = true)
@Data
public class ActivUpdate extends ActivCreate {
    @NotNull(message = "need update user")
    private String updateUser;
    @ApiModelProperty(hidden = true)
    private String createUser;
    private Integer status;
}
