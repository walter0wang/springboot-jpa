package com.epg.act.service;

import com.epg.act.entity.EggActiv;
import com.epg.act.util.Pager;
import com.epg.act.web.requestVo.ActivCreate;
import com.epg.act.web.requestVo.ActivUpdate;

public interface ActivityService {

    EggActiv getActivity(Integer id);

    Pager<EggActiv> page(int current, int size, String name, Integer status);

    void createActivity(ActivCreate requestVo);

    void updateActivity(Integer id, ActivUpdate requestVo);

    void deleteActivity(Integer id);

}
