package com.epg.act.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.epg.act.entity.EggPrize;
import com.epg.act.mapper.PrizeMapper;
import com.epg.act.service.PrizeService;
import com.epg.act.util.Pager;
import com.epg.act.web.exception.CheckException;
import com.epg.act.web.requestVo.PrizeCreate;
import com.epg.act.web.requestVo.PrizeUpdate;
import com.epg.act.web.responseVo.ResultCode;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class PrizeServiceImpl extends ServiceImpl<PrizeMapper, EggPrize> implements PrizeService {

    @Resource
    private PrizeMapper prizeMapper;

    @Override
    public EggPrize getPrize(Integer id) {
        return prizeMapper.selectById(id);
    }

    @Override
    public Pager<EggPrize> page(int current, int size, String name, Integer status) {
        Pager<EggPrize> pager = new Pager<>(current, size);
        pager.setRecords(prizeMapper.pageData(current, size, name, status));
        pager.setCount(prizeMapper.pageCount(name, status));
        return pager;
    }

    @Override
    public void create(PrizeCreate requestVo) {
        EggPrize prize = new EggPrize();
        BeanUtils.copyProperties(requestVo, prize);
        prize.setUpdateUser(prize.getCreateUser());
        if (prizeMapper.insert(prize) != 1) throw new CheckException(ResultCode.UNKNOW_DATA);
    }

    @Override
    public void update(Integer id, PrizeUpdate requestVo) {
        EggPrize prize = new EggPrize();
        com.epg.act.web.util.BeanUtils.copyProperties(requestVo, prize, true);
        prize.setId(id);
        if (prizeMapper.updateById(prize) != 1) throw new CheckException(ResultCode.UNKNOW_DATA);
    }

    @Override
    public void delete(Integer id) {
        if (prizeMapper.deleteById(id) != 1) throw new CheckException(ResultCode.UNKNOW_DATA);
    }

}
