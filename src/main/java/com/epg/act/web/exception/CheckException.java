package com.epg.act.web.exception;

import com.epg.act.web.responseVo.ResultCode;

public class CheckException extends RuntimeException{

    private Integer code;

    public CheckException(ResultCode resultCode) {
        super(resultCode.getMsg());
        this.code = resultCode.getCode();
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

}
