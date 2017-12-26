package com.epg.act.service;

import com.epg.act.entity.EggPic;
import com.epg.act.util.Pager;
import com.epg.act.web.exception.CheckException;
import com.epg.act.web.requestVo.PicTplCreate;
import com.epg.act.web.requestVo.PicTplUpdate;

public interface PicTplService {

    EggPic get(Integer id);

    Pager<EggPic> page(int current, int size, String name, Integer status);

    void create(PicTplCreate requestVo) throws CheckException;

    void update(Integer id, PicTplUpdate requestVo);

    void delete(Integer id);

}
