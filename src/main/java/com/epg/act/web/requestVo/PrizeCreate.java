package com.epg.act.web.requestVo;

import com.epg.act.web.requestVo.valid.Create;
import com.epg.act.web.requestVo.valid.Update;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Data
public class PrizeCreate {
    private String name;
    @ApiModelProperty(notes = "单位：分")
    private Integer totalMoney;
    @ApiModelProperty(notes = "单位：分")
    private Integer maxMoney;
    @Min(value = 1, message = "最小值不得小于1", groups = {Create.class, Update.class})
    @ApiModelProperty(notes = "单位：分")
    private Integer minMoney;
    @DecimalMax(value = "100.0", message = "最大值不得大于100", groups = {Create.class, Update.class})
    @DecimalMin(value = "0.0", message = "最小值不得小于0", groups = {Create.class, Update.class})
    private Float chance;
    @NotNull(message = "不可为空", groups = {Create.class})
    private String createUser;
}
