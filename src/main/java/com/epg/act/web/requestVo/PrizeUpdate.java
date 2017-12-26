package com.epg.act.web.requestVo;

import com.epg.act.web.requestVo.valid.Update;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;

@EqualsAndHashCode(callSuper = true)
@Data
public class PrizeUpdate extends PrizeCreate {
    @NotNull(message = "不可为空", groups = {Update.class})
    private String updateUser;
    @ApiModelProperty(hidden = true)
    @Null
    private String createUser;
}
