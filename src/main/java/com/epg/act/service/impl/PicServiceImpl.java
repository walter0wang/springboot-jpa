package com.epg.act.service.impl;

import com.epg.act.entity.Picture;
import com.epg.act.repository.PicRepository;
import com.epg.act.service.PicService;
import com.epg.act.web.requestVo.PicRequestVo;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;

@Service
public class PicServiceImpl implements PicService{

    @Resource
    private PicRepository picRepository;

    @Override
    public Picture getPicture(Integer id) {
        return picRepository.findOne(id);
    }

    @Override
    public Page<Picture> pagePicture(int page, int perPage, String sortBy, String order) {
        Pageable pageable = StringUtils.isEmpty(sortBy) ? new PageRequest(page, perPage) : new PageRequest(page, perPage, new Sort(Sort.Direction.ASC, sortBy));
        return picRepository.findAll(pageable);
    }

    @Override
    public Picture createPicture(Picture picture) {
        return picRepository.save(picture);
    }

    @Override
    public Picture updatePicture(Integer id, PicRequestVo requestVo) {
        Picture Picture = picRepository.findOne(id);
        if (Picture == null) {
            return null;
        }
        com.epg.act.web.util.BeanUtils.copyProperties(requestVo, Picture, true);
        return picRepository.save(Picture);
    }

    @Override
    public boolean deletePicture(Integer id) {
        try {
            picRepository.delete(id);
            return true;
        } catch (EmptyResultDataAccessException e) {
            return false;
        }
    }

}
