package com.epg.act.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.epg.act.entity.EggActiv;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.sql.Time;
import java.util.List;

public interface ActivityMapper extends BaseMapper<EggActiv> {
    List<EggActiv> pageData(@Param("current") int current, @Param("size") int size, @Param("name") String name, @Param("status") Integer status);

    Long pageCount(@Param("name") String name, @Param("status") Integer status);

    @Select("select count(ID) from EGG_ACTIV EA WHERE EA.START_TIME>#{endTime} or EA.END_TIME<#{startTime}")
    Long countByTime(@Param("startTime") Time startTime, @Param("endTime") Time endTime);
}
