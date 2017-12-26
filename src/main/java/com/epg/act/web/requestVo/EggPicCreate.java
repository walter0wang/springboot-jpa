package com.epg.act.web.requestVo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

@Data
public class EggPicCreate {

    private Long pictureId;
    @ApiModelProperty(notes = "图片类型（1：打开 2：完整 3：砸 4：提示 5：中奖未绑定 6：中奖已绑定 7：未中奖）")
    @Max(value = 7, message = "海报类型错误")
    @Min(value = 1, message = "海报类型错误")
    private Integer type;
}
