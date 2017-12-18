package com.epg.act.web.responseVo;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@Data
@NoArgsConstructor
@JsonInclude(value = JsonInclude.Include.NON_NULL)
public class ResultBean<T> implements Serializable {

    private static final long serialVersionUID = 1L;


    private int code = ResultCode.SUCCESS.getCode();
    private String msg = ResultCode.SUCCESS.getMsg();
    private T data;
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
            this.code = ((ResultCode)data).getCode();
            this.msg = ((ResultCode)data).getMsg();
        } else this.data = data;
    }

    public ResultBean(Throwable e) {
        super();
        this.code = ResultCode.UNKONW_ERROR.getCode();
        this.msg = e.toString();
    }
}
