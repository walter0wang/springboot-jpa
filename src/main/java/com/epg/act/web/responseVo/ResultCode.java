package com.epg.act.web.responseVo;


public enum ResultCode {
    UNKONW_ERROR(-1, "未知错误"),
    SUCCESS(0, "success"),
    CHECK_ERROR(1, "check err"),
    MULTI_CHECK(9998, "MULTI_CHECK"),
    ERROR_JSON(9990, "JSON parse error, ERROR json body"),
    SYSTEM_ERROR(9999, "系统错误"),
    INSERT_FAIL(9998, "插入失败"),
    UPDATE_FAIL(9997, "更新失败"),
    UNKNOW_DATA(9900, "无此数据"),
    UNKNOW_PRIZE(9901, "无此奖品"),
    UNKNOW_PIC_TPL(9902, "无此图片模板"),
    UNKNOW_ACTIV(9903, "无此活动"),
    UNKNOW_PIC(9904, "无此图片"),
    ACTIV_TIME_CONFLICT(9905, "活动时间冲突"),
    ACTIV_WEEK_ERR(9906, "活动周期错误"),
    ACTIV_CHANNEL_ERR(9907, "活动频道错误"),
    NO_FILE(9801, "上传文件为空"),
    ERR_PIC_EXT(9802, "错误的图片类型"),
    UPLOAD_FTP_ERR(9800, "上传FTP失败"),
    NO_PIC(9803,"海报不可为空"),
    ERR_PIC_TYPE(9803,"海报类型错误"),
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
