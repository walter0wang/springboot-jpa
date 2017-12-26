package com.epg.act.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.epg.act.entity.ChannelActiv;
import com.epg.act.entity.EggActiv;
import com.epg.act.mapper.*;
import com.epg.act.service.ActivityService;
import com.epg.act.util.Pager;
import com.epg.act.web.exception.CheckException;
import com.epg.act.web.requestVo.ActivCreate;
import com.epg.act.web.requestVo.ActivUpdate;
import com.epg.act.web.responseVo.ResultCode;
import com.epg.act.web.util.Constants;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.sql.Time;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

@Service
public class ActivityServiceImpl extends ServiceImpl<ActivityMapper, EggActiv> implements ActivityService {

    @Resource
    private ChannelActivMapper channelActivMapper;
    @Resource
    private PrizeMapper prizeMapper;
    @Resource
    private PicTplMapper picTplMapper;
    @Resource
    private TimeMapper timeMapper;

    @Override
    public EggActiv getActivity(Integer id) {
        return baseMapper.selectById(id);
    }

    @Override
    public Pager<EggActiv> page(int current, int size, String name, Integer status) {
        Pager<EggActiv> pager = new Pager<>(current, size);
        List<EggActiv> records = baseMapper.pageData(current, size, name, status);
        records.forEach(activ -> {
            List<Short> channels = channelActivMapper.selectChannelByActiv(activ.getId());
            activ.setChannels(channels.toArray(new Short[channels.size()]));
        });
        pager.setRecords(records);
        pager.setCount(baseMapper.pageCount(name, status));
        return pager;
    }

    @Override
    @Transactional
    public void createActivity(ActivCreate requestVo) {
        checkActiv(requestVo);

        EggActiv activity = new EggActiv();
        BeanUtils.copyProperties(requestVo, activity);
        activity.setUpdateUser(activity.getCreateUser());
        activity.setTimeNum(requestVo.getTimeNum());
        activity.setTimeBetween(requestVo.getTimeBetween());
        activity.setEndDate(activity.getEndDate());
        activity.setStatus(0);
        if (baseMapper.insert(activity) != 1) throw new CheckException(ResultCode.UNKNOW_DATA);
        if (activity.getChannels().length == 0) throw new CheckException(ResultCode.ACTIV_CHANNEL_ERR);
        Stream.of(activity.getChannels()).forEach(channel -> {
            ChannelActiv channelActiv = new ChannelActiv();
            channelActiv.setActivId(activity.getId());
            channelActiv.setChannelNum(channel);
            if (channelActivMapper.insert(channelActiv) != 1)
                throw new CheckException(ResultCode.INSERT_FAIL);
        });
    }

    @Override
    public void updateActivity(Integer id, ActivUpdate requestVo) {
        checkActiv(requestVo);
        EggActiv activ = new EggActiv();
        com.epg.act.web.util.BeanUtils.copyProperties(requestVo, activ, true);
        activ.setId(id);
        if (baseMapper.updateById(activ) != 1) throw new CheckException(ResultCode.UNKNOW_DATA);
    }

    private void checkActiv(ActivCreate requestVo){
        if (!checkActivTime(requestVo.getStartTime(), requestVo.getEndTime()))
            throw new CheckException(ResultCode.ACTIV_TIME_CONFLICT);

        if (!StringUtils.isEmpty(requestVo.getWeeks())) {
            String[] weekArr = requestVo.getWeeks().split("");
            if (Arrays.stream(weekArr).distinct().allMatch(Constants.WEEK_STR::contains))
                requestVo.setWeeks(Arrays.stream(weekArr).distinct().reduce("", String::concat));
            else throw new CheckException(ResultCode.ACTIV_WEEK_ERR);
        }

        if (!checkPrizeId(requestVo.getPrizeTplId()))
            throw new CheckException(ResultCode.UNKNOW_PRIZE);

        if (!checkPicTplId(requestVo.getPicTplId()))
            throw new CheckException(ResultCode.UNKNOW_PIC_TPL);
    }

    @Override
    public void deleteActivity(Integer id) {
        if (baseMapper.deleteById(id) != 1) throw new CheckException(ResultCode.UNKNOW_DATA);
    }

    private boolean checkPrizeId(Integer prizeId) {
        return prizeMapper.selectById(prizeId) != null;
    }

    private boolean checkPicTplId(Integer picTplId) {
        return picTplMapper.selectById(picTplId) != null;
    }

    private boolean checkActivTime(Time startTime, Time endTime) {
        return baseMapper.countByTime(startTime, endTime) > 0;
    }

}
