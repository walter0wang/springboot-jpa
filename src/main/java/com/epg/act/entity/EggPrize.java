package com.epg.act.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * @author WANGTAO
 * @since  2017/11/30 15:20
 */
@Entity
@Table(name = "EGG_PRIZE", schema = "egg", catalog = "")
@Data
@EqualsAndHashCode
public class EggPrize {
    @Id
    @Column(name = "ID")
    @GeneratedValue
    private Integer id;
    @Basic
    @Column(name = "NAME")
    private String name;
    @Basic
    @Column(name = "TOTAL_MONEY")
    private Integer totalMoney;
    @Basic
    @Column(name = "MAX_MONEY")
    private Integer maxMoney;
    @Basic
    @Column(name = "MIN_MONEY")
    private Integer minMoney;
    @Basic
    @Column(name = "CHANCE")
    private Float chance;
    @Basic
    @Column(name = "CREATE_USER")
    private String createUser;
    @Basic
    @Column(name = "CREATE_TIME", insertable = false)
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:SS")
    private Timestamp createTime;
    @Basic
    @Column(name = "UPDATE_TIME", updatable = false, insertable = false)
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:SS")
    private Timestamp updateTime;
    @Basic
    @Column(name = "UPDATE_USER")
    private String updateUser;

}
