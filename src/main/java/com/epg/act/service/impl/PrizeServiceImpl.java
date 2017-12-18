package com.epg.act.service.impl;

import com.epg.act.entity.EggPrize;
import com.epg.act.repository.PrizeRepository;
import com.epg.act.service.PrizeService;
import com.epg.act.web.requestVo.PrizeRequestVo;
import org.springframework.beans.BeanUtils;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;

@Service
public class PrizeServiceImpl implements PrizeService{

    @Resource
    private PrizeRepository prizeRepository;

    @Override
    public EggPrize getPrize(Integer id) {
        return prizeRepository.findOne(id);
    }

    @Override
    public Page<EggPrize> pagePrize(int page, int perPage, String sortBy, String order) {
        Pageable pageable = StringUtils.isEmpty(sortBy) ? new PageRequest(page, perPage) : new PageRequest(page, perPage, new Sort(Sort.Direction.ASC, sortBy));
        return prizeRepository.findAll(pageable);
    }

    @Override
    public EggPrize createPrize(PrizeRequestVo requestVo) {
        EggPrize prize = new EggPrize();
        BeanUtils.copyProperties(requestVo, prize);
        prize.setUpdateUser(prize.getCreateUser());
        return prizeRepository.save(prize);
    }

    @Override
    public EggPrize updatePrize(Integer id, PrizeRequestVo requestVo) {
        EggPrize prize = prizeRepository.findOne(id);
        if (prize == null) {
            return null;
        }
        com.epg.act.web.util.BeanUtils.copyProperties(requestVo, prize, true);
        return prizeRepository.save(prize);
    }

    @Override
    public boolean deletePrize(Integer id) {
        try {
            prizeRepository.delete(id);
            return true;
        } catch (EmptyResultDataAccessException e) {
            return false;
        }
    }

}
