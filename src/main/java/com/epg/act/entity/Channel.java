package com.epg.act.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * @author WANGTAO
 * @Create 2017/11/30 15:20
 */
@Entity
@Table(name = "CHANNEL", schema = "egg", catalog = "")
@Data
@EqualsAndHashCode
public class Channel {
    @Id
    @Column(name = "NUM")
    private Short num;
    @Basic
    @Column(name = "NAME")
    private String name;
    @Basic
    @Column(name = "CREATE_TIME", insertable = false)
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:SS")
    private Timestamp createTime;
    @Basic
    @Column(name = "CREATE_USER")
    private String createUser;
}
