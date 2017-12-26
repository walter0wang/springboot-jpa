package com.epg.act.service;

import com.epg.act.entity.EggTime;
import com.epg.act.util.Pager;
import com.epg.act.web.exception.CheckException;
import com.epg.act.web.requestVo.TimeCreate;
import com.epg.act.web.requestVo.TimeUpdate;

import java.util.List;

public interface TimeService {

    List<EggTime> create(TimeCreate requestVo) throws CheckException;

    EggTime get(Long id) throws CheckException;

    Pager<EggTime> page(int current, int size);

    void update(Long id, TimeUpdate requestVo) throws CheckException;

    void delete(Long id) throws CheckException;

}
