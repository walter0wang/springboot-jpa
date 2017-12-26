package com.epg.act.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.epg.act.entity.Picture;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface PicMapper extends BaseMapper<Picture> {
    List<Picture> pageData(@Param("current") int current, @Param("size") int size, @Param("name") String name);

    Integer pageCount(@Param("name") String name);
}
