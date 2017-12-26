package com.epg.act.service;

import com.epg.act.entity.Picture;
import com.epg.act.util.Pager;
import com.epg.act.web.exception.CheckException;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface PicService {

    Picture get(Long id);

    Pager<Picture> page(int current, int size, String name);

    void create(MultipartFile file, String createUser) throws CheckException, IOException;

    void delete(Long id);

}
