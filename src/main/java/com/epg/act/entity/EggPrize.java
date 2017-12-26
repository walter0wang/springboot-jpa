package com.epg.act.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.enums.IdType;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.sql.Timestamp;

/**
 * @author WANGTAO
 * @since 2017/11/30 15:20
 */
@Data
@EqualsAndHashCode
public class EggPrize {
    @TableId(type = IdType.AUTO)
    private Integer id;
    private String name;
    private Integer totalMoney;
    private Integer maxMoney;
    private Integer minMoney;
    private Float chance;
    private String createUser;
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private Timestamp createTime;
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private Timestamp updateTime;
    private String updateUser;

    @TableField(exist = false)
    private String activName;
    @TableField(exist = false)
    private String activStatus;

}
