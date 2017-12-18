package com.epg.act.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * @author WANGTAO WangTao
 * @since 2017/11/30 15:20
 */
@Entity
@Table(name = "EGG_PIC_TPL", schema = "egg", catalog = "")
@Data
@EqualsAndHashCode
public class EggPicTpl {
    @Id
    @Column(name = "ID")
    @GeneratedValue
    private Integer id;
    @Basic
    @Column(name = "NAME")
    private String name;
    @Basic
    @Column(name = "TYPE")
    private Byte type;
    @Basic
    @Column(name = "NUM")
    private Integer num;
    @Basic
    @Column(name = "CREATE_TIME", insertable = false)
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:SS")
    private Timestamp createTime;
    @Basic
    @Column(name = "CREATE_USER")
    private String createUser;
    @Basic
    @Column(name = "UPDATE_TIME", updatable = false)
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:SS")
    private Timestamp updateTime;
    @Basic
    @Column(name = "UPDATE_USER")
    private String updateUser;

}
