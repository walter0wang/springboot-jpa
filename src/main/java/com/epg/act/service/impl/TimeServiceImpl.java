package com.epg.act.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.epg.act.entity.EggActiv;
import com.epg.act.entity.EggTime;
import com.epg.act.mapper.ActivityMapper;
import com.epg.act.mapper.TimeMapper;
import com.epg.act.service.TimeService;
import com.epg.act.util.Pager;
import com.epg.act.web.exception.CheckException;
import com.epg.act.web.requestVo.TimeCreate;
import com.epg.act.web.requestVo.TimeUpdate;
import com.epg.act.web.responseVo.ResultCode;
import com.epg.act.web.util.RandomUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class TimeServiceImpl extends ServiceImpl<TimeMapper, EggTime> implements TimeService {
    @Resource
    private ActivityMapper activityMapper;

    @Override
    public EggTime get(Long id) {
        return baseMapper.selectById(id);
    }

    @Override
    public Pager<EggTime> page(int current, int size) {
        Pager<EggTime> pager = new Pager<>(current, size);
        pager.setRecords(baseMapper.pageData(current, size));
        pager.setCount(baseMapper.pageCount());
        return pager;
    }

    @Override
    public void update(Long id, TimeUpdate requestVo) throws CheckException {
        EggTime time = new EggTime();
        com.epg.act.web.util.BeanUtils.copyProperties(requestVo, time, true);
        time.setId(id);
        if (baseMapper.updateById(time) != 1) throw new CheckException(ResultCode.UNKNOW_DATA);
    }

    @Override
    public void delete(Long id) throws CheckException {
        if (baseMapper.deleteById(id) != 1) throw new CheckException(ResultCode.UNKNOW_DATA);
    }

    @Override
    @Transactional
    public List<EggTime> create(TimeCreate requestVo) {
        EggActiv activ = activityMapper.selectById(requestVo.getActivId());
        if (activ == null) throw new CheckException(ResultCode.UNKNOW_ACTIV);
        baseMapper.deleteByActivId(activ.getId());
        return makeTimes(activ, requestVo.getUpdateUser());
    }

    private List<EggTime> makeTimes(EggActiv activ, String updateUser) {
        List<EggTime> eggTimes = new ArrayList<>(activ.getTimeNum());
        Integer timeBetween = activ.getTimeBetween(), //时间间隔
                timesNum = activ.getTimeNum(); // 时间点数量
        long restMilliseconds, startTime;
        if (StringUtils.isEmpty(activ.getWeeks())) { // 固定时间
            Date start = new Date(activ.getStartDate().getTime() + activ.getStartTime().getTime());
            Date end = new Date(activ.getEndDate().getTime() + activ.getEndTime().getTime());
            startTime = start.getTime();
            restMilliseconds = end.getTime() - startTime - (timeBetween * timesNum * 60 * 1000);
        } else { // 每周
            startTime = activ.getStartTime().getTime();
            restMilliseconds = activ.getEndTime().getTime() - startTime;
        }
        if (restMilliseconds < 0) return null;
        List<BigDecimal> randomTimes = RandomUtil.getAllHotPacket(restMilliseconds,
                activ.getTimeNum() + 1, 2d, 3d);

        for (int i = 0; i < activ.getTimeNum(); i++) {
            startTime += randomTimes.get(i).longValue();
            EggTime eggTime = new EggTime();
            eggTime.setActivId(activ.getId());
            eggTime.setUpdateUser(updateUser);
            eggTime.setDatetime(new Timestamp(startTime));
            baseMapper.insert(eggTime);
            eggTimes.add(eggTime);
        }
        return eggTimes;
    }
}
