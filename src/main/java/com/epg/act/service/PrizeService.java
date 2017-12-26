package com.epg.act.service;

import com.epg.act.entity.EggPrize;
import com.epg.act.util.Pager;
import com.epg.act.web.requestVo.PrizeCreate;
import com.epg.act.web.requestVo.PrizeUpdate;

public interface PrizeService {

    EggPrize getPrize(Integer id);

    Pager<EggPrize> page(int current, int size, String name, Integer status);

    void create(PrizeCreate requestVo);

    void update(Integer id, PrizeUpdate requestVo);

    void delete(Integer id);

}
