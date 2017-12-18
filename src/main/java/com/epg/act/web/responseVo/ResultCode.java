package com.epg.act.web.responseVo;


public enum ResultCode {
    UNKONW_ERROR(-1, "未知错误"),
    SUCCESS(0, "success"),
    CHECK_ERROR(1, "check err"),
    MULTI_CHECK(9998, "MULTI_CHECK"),
    ERROR_JSON(9990, "JSON parse error, ERROR json body"),
    SYSTEM_ERROR(9999, "系统错误"),
    UNKNOW_DATA(9900, "无此数据"),
    ;

    private Integer code;
    private String msg;

    ResultCode(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
