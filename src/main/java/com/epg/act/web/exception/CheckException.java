package com.epg.act.web.exception;

import com.epg.act.web.responseVo.ResultCode;

public class CheckException extends RuntimeException {

    private ResultCode code;

    public CheckException(ResultCode resultCode) {
        super(resultCode.getMsg());
        this.code = resultCode;
    }

    public ResultCode getCode() {
        return code;
    }

    public void setCode(ResultCode code) {
        this.code = code;
    }
}
