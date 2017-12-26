package com.epg.act.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.annotation.Id;

import java.sql.Timestamp;

/**
 * @author WangTao
 * @since create 2017/11/30 15:20
 */
@Data
@EqualsAndHashCode
public class EggTime {
    @Id
    private Long id;
    private Integer activId;
//    private Short channelNum;
//    private Integer week;
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private Timestamp datetime;
    private Integer picTplId;
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private Timestamp updateTime;
    private String updateUser;

    public Timestamp getDatetime() {
        return datetime;
    }

}
