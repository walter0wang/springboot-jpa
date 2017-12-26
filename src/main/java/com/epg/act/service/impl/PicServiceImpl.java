package com.epg.act.service.impl;

import com.epg.act.entity.Picture;
import com.epg.act.mapper.PicMapper;
import com.epg.act.service.PicService;
import com.epg.act.util.FtpUtils;
import com.epg.act.util.Pager;
import com.epg.act.web.exception.CheckException;
import com.epg.act.web.responseVo.ResultCode;
import com.epg.act.web.util.Utils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.ResourceBundle;

@Service
public class PicServiceImpl implements PicService {

    private final String ROOT = ResourceBundle.getBundle("ftp").getString("pic.directory");

    @Resource
    private PicMapper picMapper;

    @Override
    public Picture get(Long id) {
        return picMapper.selectById(id);
    }

    @Override
    public Pager<Picture> page(int current, int size, String name) {
        Pager<Picture> pager = new Pager<>(current, size);
        pager.setRecords(picMapper.pageData(current, size, name));
        pager.setCount(picMapper.pageCount(name));
        return pager;
    }

    @Override
    public void create(MultipartFile file, String createUser) throws CheckException, IOException {
        if (!file.isEmpty()) {
            String origName = file.getOriginalFilename();
            if (!Utils.checkPicExt(origName)) throw new CheckException(ResultCode.ERR_PIC_EXT);

            if (!FtpUtils.upload(FtpUtils.Instance.PIC, ROOT, file.getInputStream(), file.getOriginalFilename()))
                throw new CheckException(ResultCode.UPLOAD_FTP_ERR);

            String ext = origName.substring(origName.lastIndexOf(".") + 1, origName.length());
            Picture picture = new Picture();
            picture.setName(origName);
            picture.setUploadName(Utils.generatePicName(ext));
            picture.setCreateUser(createUser);
            picMapper.insert(picture);
        } else throw new CheckException(ResultCode.NO_FILE);
    }

    @Override
    public void delete(Long id) {
        if (picMapper.deleteById(id) != 1) throw new CheckException(ResultCode.UNKNOW_DATA);
    }

}
