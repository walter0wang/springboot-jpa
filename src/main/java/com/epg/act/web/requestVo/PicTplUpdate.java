package com.epg.act.web.requestVo;

import com.epg.act.web.requestVo.valid.Update;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@EqualsAndHashCode(callSuper = false)
@Data
public class PicTplUpdate extends PicTplCreate{
    @NotNull(message = "不可为空")
    private Long id;
    @Valid
    private EggPicCreate[] create;
    @ApiModelProperty(notes = "更新的金蛋图片")
    @Valid
    private EggPicUpdate[] update;
    @ApiModelProperty(notes = "删除的金蛋图片id")
    private Long[] delete;
    @ApiModelProperty(hidden = true)
    private String createUser;
    @ApiModelProperty(notes = "编辑时必填")
    @NotNull(message = "必填", groups = {Update.class})
    private String updateUser;
}
