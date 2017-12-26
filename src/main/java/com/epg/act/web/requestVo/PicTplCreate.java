package com.epg.act.web.requestVo;

import com.epg.act.web.requestVo.valid.Create;
import com.epg.act.web.requestVo.valid.Update;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Data
public class PicTplCreate {
    @NotNull(message = "名称不可为空", groups = {Create.class, Update.class})
    private String name;
    @ApiModelProperty(notes = "创建时必填")
    @NotNull(message = "必填", groups = {Create.class})
    private String createUser;
    @ApiModelProperty(example = "1", notes = "模板类型 （1:标清 2:高清）")
    @NotNull(message = "必填", groups = {Create.class, Update.class})
    private Integer type;
    @ApiModelProperty(notes = "创建的金蛋图片")
    @NotNull(message = "不可为空", groups = {Create.class})
    @Valid
    private EggPicCreate[] create;
}
