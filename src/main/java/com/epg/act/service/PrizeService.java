package com.epg.act.service;

import com.epg.act.entity.EggPrize;
import com.epg.act.web.requestVo.PrizeRequestVo;
import org.springframework.data.domain.Page;

public interface PrizeService {

    EggPrize getPrize(Integer id);

    Page<EggPrize> pagePrize(int page,int perPage, String sortBy, String order);

    EggPrize createPrize(PrizeRequestVo requestVo);

    EggPrize updatePrize(Integer id, PrizeRequestVo requestVo);

    boolean deletePrize(Integer id);

}
