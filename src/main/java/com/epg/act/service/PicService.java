package com.epg.act.service;

import com.epg.act.entity.Picture;
import com.epg.act.web.requestVo.PicRequestVo;
import org.springframework.data.domain.Page;

public interface PicService {

    Picture getPicture(Integer id);

    Page<Picture> pagePicture(int page, int perPage, String sortBy, String order);

    Picture createPicture(Picture picture);

    Picture updatePicture(Integer id, PicRequestVo requestVo);

    boolean deletePicture(Integer id);

}
