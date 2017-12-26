package com.epg.act.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.epg.act.entity.EggTime;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TimeMapper extends BaseMapper<EggTime> {

    @Delete("delete from EGG_TIME where ACTIV_ID=${activId}")
    int deleteByActivId(@Param("activId") Integer activId);

    List<EggTime> pageData(@Param("current") int current, @Param("size") int size);

    Long pageCount();
}
