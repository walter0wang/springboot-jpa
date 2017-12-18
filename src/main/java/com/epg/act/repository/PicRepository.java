package com.epg.act.repository;

import com.epg.act.entity.Picture;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface PicRepository extends PagingAndSortingRepository<Picture, Integer> {
}
