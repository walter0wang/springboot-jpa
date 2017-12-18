package com.epg.act.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;

/**
 * @author WANGTAO WangTao
 * @since 2017/11/30 15:20
 */
@Entity
@Table(name = "CHANNEL_ACTIV", schema = "egg", catalog = "")
@Data
@EqualsAndHashCode
public class ChannelActiv {
    @Id
    @Column(name = "ID")
    private Integer id;
    @Basic
    @Column(name = "CHANNEL_NUM")
    private Short channelNum;
    @Basic
    @Column(name = "ACTIV_ID")
    private Integer activId;

}
