package com.epg.act.repository;

import com.epg.act.entity.EggPrize;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface PrizeRepository extends PagingAndSortingRepository<EggPrize, Integer> {
}
