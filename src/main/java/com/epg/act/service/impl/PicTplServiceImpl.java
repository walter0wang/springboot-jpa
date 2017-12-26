package com.epg.act.service.impl;

import com.epg.act.entity.EggPic;
import com.epg.act.entity.EggPicTpl;
import com.epg.act.entity.Picture;
import com.epg.act.mapper.EggPicMapper;
import com.epg.act.mapper.PicMapper;
import com.epg.act.mapper.PicTplMapper;
import com.epg.act.service.PicTplService;
import com.epg.act.util.Pager;
import com.epg.act.web.exception.CheckException;
import com.epg.act.web.requestVo.EggPicCreate;
import com.epg.act.web.requestVo.EggPicUpdate;
import com.epg.act.web.requestVo.PicTplCreate;
import com.epg.act.web.requestVo.PicTplUpdate;
import com.epg.act.web.responseVo.ResultCode;
import com.epg.act.web.util.Utils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Arrays;

@Service
public class PicTplServiceImpl implements PicTplService {

    @Resource
    private EggPicMapper eggPicMapper;
    @Resource
    private PicTplMapper picTplMapper;
    @Resource
    private PicMapper picMapper;


    @Override
    public EggPic get(Integer id) {
        return eggPicMapper.selectById(id);
    }

    @Override
    public Pager<EggPic> page(int current, int size, String name, Integer status) {
        Pager<EggPic> pager = new Pager<>(current, size);
        pager.setRecords(eggPicMapper.pageData(current, size, name, status));
        pager.setCount(eggPicMapper.pageCount(name, status));
        return pager;
    }

    @Override
    @Transactional
    public void create(PicTplCreate requestVo) throws CheckException {
        EggPicCreate[] eggPics = requestVo.getCreate();
        if (eggPics != null && eggPics.length > 0) {

            EggPicTpl picTpl = new EggPicTpl();
            BeanUtils.copyProperties(requestVo, picTpl);
            picTpl.setUpdateUser(requestVo.getCreateUser());
            picTpl.setNum(eggPics.length);
            if (picTplMapper.insert(picTpl) != 1)
                throw new CheckException(ResultCode.INSERT_FAIL);
            else createEggPic(eggPics, picTpl.getId());
        } else
            throw new CheckException(ResultCode.NO_PIC);
    }

    private void createEggPic(EggPicCreate[] eggPics, Integer picTplId) throws CheckException {
        if (Arrays.stream(eggPics).noneMatch(PicTplServiceImpl::checkEggPic))
            throw new CheckException(ResultCode.ERR_PIC_TYPE);
        Arrays.stream(eggPics).forEach(e -> {
            Picture picture = picMapper.selectById(e.getPictureId());
            if (picture == null) throw new CheckException(ResultCode.UNKNOW_PIC);
            com.epg.act.entity.EggPic eggPic = new EggPic();
            BeanUtils.copyProperties(e, eggPic);
            BeanUtils.copyProperties(picture, eggPic);
            eggPic.setPicTplId(picTplId);
            if (eggPicMapper.insert(eggPic) != 1)
                throw new CheckException(ResultCode.INSERT_FAIL);
        });
    }

    private void updateEggPic(EggPicUpdate[] eggPics) throws CheckException {
        if (Arrays.stream(eggPics).noneMatch(PicTplServiceImpl::checkEggPic))
            throw new CheckException(ResultCode.ERR_PIC_TYPE);
        Arrays.stream(eggPics).forEach(e -> {
            com.epg.act.entity.EggPic eggPic = new EggPic();
            BeanUtils.copyProperties(e, eggPic);
            if (eggPicMapper.updateById(eggPic) != 1)
                throw new CheckException(ResultCode.UPDATE_FAIL);
        });
    }

    private static boolean checkEggPic(EggPicCreate eggPic) {
        return Utils.checkEggPicType(eggPic.getType());
    }

    @Override
    @Transactional
    public void update(Integer id, PicTplUpdate requestVo) {
        EggPicTpl picTpl = new EggPicTpl();
        com.epg.act.web.util.BeanUtils.copyProperties(requestVo, picTpl);
        picTpl.setId(id);
        if (picTplMapper.updateById(picTpl) != 1) throw new CheckException(ResultCode.UNKNOW_DATA);
        if (requestVo.getCreate() != null && requestVo.getCreate().length > 0) {
            createEggPic(requestVo.getCreate(), picTpl.getId());
        }
        if (requestVo.getUpdate() != null && requestVo.getUpdate().length >0){
            updateEggPic(requestVo.getUpdate());
        }
        if (requestVo.getDelete() != null && requestVo.getDelete().length >0){
            eggPicMapper.deleteBatchIds(new ArrayList<>(Arrays.asList(requestVo.getDelete())));
        }
    }

    @Override
    public void delete(Integer id) {
        if (picTplMapper.deleteById(id) != 1) throw new CheckException(ResultCode.UNKNOW_DATA);
    }

}
