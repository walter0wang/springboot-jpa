package com.epg.act.service.impl;

import com.epg.act.entity.EggActiv;
import com.epg.act.entity.QEggActiv;
import com.epg.act.repository.ActivityRepository;
import com.epg.act.service.ActivityService;
import com.epg.act.web.requestVo.ActivityRequestVo;
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
public class ActivityServiceImpl implements ActivityService{

    @Resource
    private ActivityRepository activityRepository;

    @Override
    public EggActiv getActivity(Integer id) {
        QEggActiv eggActiv = QEggActiv.eggActiv;
        System.out.println(activityRepository.findAll());
        return activityRepository.findOne(id);
    }

    @Override
    public Page<EggActiv> pageActivity(int page, int perPage, String sortBy, String order) {
        Pageable pageable = StringUtils.isEmpty(sortBy) ? new PageRequest(page, perPage) : new PageRequest(page, perPage, new Sort(Sort.Direction.ASC, sortBy));
        return activityRepository.findAll(pageable);
    }

    @Override
    public EggActiv createActivity(ActivityRequestVo requestVo) {
        EggActiv activity = new EggActiv();
        BeanUtils.copyProperties(requestVo, activity);
        activity.setUpdateUser(activity.getCreateUser());
        activity.setTimeNum(requestVo.getTimeNum().byteValue());
        activity.setTimeBetween(requestVo.getTimeBetween().byteValue());
        activity.setEndDate(activity.getEndDate());
        activity.setStatus((byte)1);
        return activityRepository.save(activity);
    }

    @Override
    public EggActiv updateActivity(Integer id, ActivityRequestVo requestVo) {
        EggActiv Activity = activityRepository.findOne(id);
        if (Activity == null) {
            return null;
        }
        com.epg.act.web.util.BeanUtils.copyProperties(requestVo, Activity, true);
        return activityRepository.save(Activity);
    }

    @Override
    public boolean deleteActivity(Integer id) {
        try {
            activityRepository.delete(id);
            return true;
        } catch (EmptyResultDataAccessException e) {
            return false;
        }
    }

}
