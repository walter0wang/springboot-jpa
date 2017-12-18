package com.epg.act.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * @Author WANGTAO WangTao
 * @Create 2017/11/30 15:20
 */
@Entity
@Table(name = "EGG_TIME", schema = "egg", catalog = "")
@Data
@EqualsAndHashCode
public class EggTime {
    @Id
    @Column(name = "ID")
    @GeneratedValue
    private Long id;
    @Basic
    @Column(name = "ACTIV_ID")
    private Integer activId;
    @Basic
    @Column(name = "CHANNEL_NUM")
    private Short channelNum;
    @Basic
    @Column(name = "WEEK")
    private Integer week;
    @Basic
    @Column(name = "DATETIME")
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:SS")
    private Timestamp datetime;
    @Basic
    @Column(name = "PIC_TPL_ID")
    private Integer picTplId;
    @Basic
    @Column(name = "UPDATE_TIME", updatable = false, insertable =false) // 不更新该字段
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:SS")
    private Timestamp updateTime;
    @Basic
    @Column(name = "UPDATE_USER")
    private String updateUser;
}
