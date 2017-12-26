package com.epg.act.web.requestVo;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class EggPicUpdate extends EggPicCreate {
    private Long id;
}