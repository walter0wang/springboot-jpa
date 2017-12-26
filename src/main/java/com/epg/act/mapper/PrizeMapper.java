package com.epg.act.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.epg.act.entity.EggPrize;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface PrizeMapper extends BaseMapper<EggPrize> {

    List<EggPrize> pageData(@Param("current") int current, @Param("size") int size, @Param("name") String name, @Param("status") Integer status);

    Integer pageCount(@Param("name") String name, @Param("status") Integer status);
}
