package com.epg.act.web.responseVo;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@Data
@NoArgsConstructor
@JsonInclude(value = JsonInclude.Include.NON_NULL)
@ApiModel(value = "返回值模型", description = "返回值模型")
public class ResultBean<T> implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "返回码")
    private int code = ResultCode.SUCCESS.getCode();
    @ApiModelProperty(value = "返回消息")
    private String msg = ResultCode.SUCCESS.getMsg();
    @ApiModelProperty(value = "返回数据")
    private T data;
    @ApiModelProperty(value = "返回当前请求时间戳")
    private long timestamp = new Date().getTime();

    public ResultBean(ResultCode code, T data) {
        super();
        if (code != null) {
            this.code = code.getCode();
            this.msg = code.getMsg();
        }
        this.data = data;
    }

    public ResultBean(T data) {
        super();
        if (data instanceof ResultCode) {
            this.code = ((ResultCode) data).getCode();
            this.msg = ((ResultCode) data).getMsg();
        } else this.data = data;
    }

    public ResultBean(Throwable e) {
        super();
        this.code = ResultCode.UNKONW_ERROR.getCode();
        this.msg = e.toString();
    }
}
