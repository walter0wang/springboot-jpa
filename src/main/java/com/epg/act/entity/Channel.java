package com.epg.act.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.annotation.Id;

import java.sql.Timestamp;

/**
 * @author WANGTAO
 * @since create 2017/11/30 15:20
 */
@Data
@EqualsAndHashCode
public class Channel {
    @Id
    private Short num;
    private String name;
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private Timestamp createTime;
    private String createUser;
}
