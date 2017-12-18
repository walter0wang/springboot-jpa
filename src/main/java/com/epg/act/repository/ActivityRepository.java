package com.epg.act.repository;

import com.epg.act.entity.EggActiv;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

public interface ActivityRepository extends PagingAndSortingRepository<EggActiv, Integer>, CrudRepository<EggActiv, Integer>, QueryDslPredicateExecutor<EggActiv> {

    @Query(value = "select a.*,p.NAME AS pName from EGG_ACTIV a LEFT JOIN EGG_PRIZE p ON a.PRIZE_TPL_ID = p.ID where a.id=:id", nativeQuery = true)
    Object[] findActivityInfo(@Param("id") Integer id);
}
