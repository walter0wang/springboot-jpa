package com.epg.act.web.requestVo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.sql.Time;
import java.sql.Timestamp;

/**
 * @author WANGTAO
 * @since  2017-12-13 17:24:59
 */
@Data
public class ActivityRequestVo {
    private String name;
    private Integer picTplId;
    private Integer prizeTplId;
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd")
    private Timestamp startDate;
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd")
    private Timestamp endDate;
    private String weeks;
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd 23:59:59")
    private Time startTime;
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd 23:59:59")
    private Time endTime;
    private Integer timeNum;
    private Integer timeBetween;
    private String updateUser;
    private String createUser;
}
