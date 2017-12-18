package com.epg.act.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * @author WANGTAO
 * @since create 2017/11/30 15:20
 */
@Entity
@Table(name = "PICTURE", schema = "egg", catalog = "")
@Data
@EqualsAndHashCode
public class Picture {
    @Id
    @Column(name = "ID")
    @GeneratedValue
    private Integer id;
    @Basic
    @Column(name = "UPLOAD_NAME")
    private String uploadName;
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
