package com.epg.act.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.epg.act.entity.ChannelActiv;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface ChannelActivMapper extends BaseMapper<ChannelActiv> {
//    List<EggActiv> pageData(@Param("current") int current, @Param("size") int size, @Param("name") String name, @Param("status") Integer status);
//
//    Integer pageCount(@Param("name") String name, @Param("status") Integer status);

    @Select("SELECT CA.CHANNEL_NUM FROM CHANNEL_ACTIV CA WHERE ACTIV_ID=#{activId}")
    List<Short> selectChannelByActiv(@Param("activId") int activId);
}
