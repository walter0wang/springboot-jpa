package com.epg.act.web.requestVo;

import lombok.Data;

import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Min;

/**
 * @author WANGTAO
 * @since 2017/11/30 9:55
 */
@Data
public class PrizeRequestVo {
    private String name;
    private Integer totalMoney;
    private Integer maxMoney;
    @Min(value = 0, message = "最小值不得小于0")
    private Integer minMoney;
    @DecimalMax(value = "100.0", message = "最大值不得大于100")
    @DecimalMin(value = "0.0", message = "最小值不得小于0")
    private Float chance;
    private String createUser;
    private String updateUser;
}
