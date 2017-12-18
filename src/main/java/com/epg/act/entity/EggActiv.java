package com.epg.act.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.sql.Time;
import java.sql.Timestamp;

/**
 * @author WANGTAO WangTao
 * @since  2017/11/30 15:20
 */
@Entity
@Table(name = "EGG_ACTIV", schema = "egg", catalog = "")
@Data
@EqualsAndHashCode
public class EggActiv {
    @Id
    @Column(name = "ID")
    @GeneratedValue
    private Integer id;
    @Basic
    @Column(name = "NAME")
    private String name;
    @Basic
    @Column(name = "PIC_TPL_ID")
    private Integer picTplId;
    @Basic
    @Column(name = "PRIZE_TPL_ID")
    private Integer prizeTplId;
    @Basic
    @Column(name = "STATUS")
    private Byte status;
    @Basic
    @Column(name = "START_DATE")
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:SS")
    private Timestamp startDate;
    @Basic
    @Column(name = "END_DATE")
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd 23:59:59")
    private Timestamp endDate;
    @Basic
    @Column(name = "WEEKS")
    private String weeks;
    @Basic
    @Column(name = "START_TIME")
    private Time startTime;
    @Basic
    @Column(name = "END_TIME")
    private Time endTime;
    @Basic
    @Column(name = "TIME_NUM")
    private Byte timeNum;
    @Basic
    @Column(name = "TIME_BETWEEN")
    private Byte timeBetween;
    @Basic
    @Column(name = "CREATE_TIME", insertable = false)
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:SS")
    private Timestamp createTime;
    @Basic
    @Column(name = "CREATE_USER")
    private String createUser;
    @Basic
    @Column(name = "UPDATE_TIME", updatable = false, insertable = false)
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:SS")
    private Timestamp updateTime;
    @Basic
    @Column(name = "UPDATE_USER")
    private String updateUser;

}
