package com.epg.act.entity;

import javax.persistence.*;

/**
 * @author WANGTAO WangTao
 * @create 2017/11/30 15:20
 */
@Entity
@Table(name = "EGG_PIC", schema = "egg", catalog = "")
public class EggPic {
    @Id
    @Column(name = "ID")
    @GeneratedValue
    private Long id;
    @Basic
    @Column(name = "PIC_TPL_ID")
    private Integer picTplId;
    @Basic
    @Column(name = "TYPE")
    private Byte type;
    @Basic
    @Column(name = "UPLOAD_NAME")
    private String uploadName;
    @Basic
    @Column(name = "NAME")
    private String name;
}
