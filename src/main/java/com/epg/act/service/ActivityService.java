package com.epg.act.service;

import com.epg.act.entity.EggActiv;
import com.epg.act.web.requestVo.ActivityRequestVo;
import org.springframework.data.domain.Page;

public interface ActivityService {

    EggActiv getActivity(Integer id);

    Page<EggActiv> pageActivity(int page, int perPage, String sortBy, String order);

    EggActiv createActivity(ActivityRequestVo requestVo);

    EggActiv updateActivity(Integer id, ActivityRequestVo requestVo);

    boolean deleteActivity(Integer id);

}
