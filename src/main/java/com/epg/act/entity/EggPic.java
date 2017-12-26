package com.epg.act.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;

/**
 * @author WangTao
 * @since create 2017/11/30 15:20
 */
@Data
public class EggPic {
    @Id
    private Long id;
    private Integer picTplId;
    private Integer type;
    private String uploadName;
    private String name;
}
