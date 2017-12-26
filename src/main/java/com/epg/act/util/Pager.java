package com.epg.act.util;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;

@Data
@JsonInclude(value = JsonInclude.Include.NON_NULL)
@ApiModel(value = "分页对象", description = "分页对象")
public class Pager<T> implements Serializable {

    @ApiModelProperty(value = "当前页索引 默认0（第一页）", example = "0")
    private int current = 0;
    @ApiModelProperty(value = "每页数据数 默认20", example = "20")
    private int size = 20;
    @ApiModelProperty(value = "每页数据")
    private List<T> records = Collections.emptyList();
    @ApiModelProperty(value = "总数据条数")
    private long count;

    public Pager(int current, int size) {
        super();
        this.current = current;
        this.size = size;
    }
}
