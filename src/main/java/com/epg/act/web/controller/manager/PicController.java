package com.epg.act.web.controller.manager;

import com.epg.act.entity.Picture;
import com.epg.act.service.PicService;
import com.epg.act.util.FtpUtils;
import com.epg.act.web.requestVo.PicRequestVo;
import com.epg.act.web.responseVo.ResultBean;
import com.epg.act.web.responseVo.ResultCode;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.IOException;

/**
 * picture manager
 *
 * @author WANGTAO
 * @since 2017/12/1 16:20
 */
@Api(value = "PicController", description = "奖品接口")
@RestController
@RequestMapping("/egg")
@Slf4j
public class PicController {

    @Resource
    private PicService picService;

    static final String ROOT = "upload-dir";

    /**
     * TODO description
     *
     * @param page, perPage, sortby, order
     * @return com.epg.act.web.responseVo.ResultBean<org.springframework.data.domain.Page>
     * @author WangTao
     * @since Created in 2017/11/30 9:51
     */
    @ApiOperation(value = "奖品列表", notes = "奖品列表")
    @GetMapping("/pictures")
    public ResultBean<Page> page(@RequestParam(defaultValue = "1") int page,
                                 @RequestParam(defaultValue = "20") int perPage,
                                 @RequestParam(required = false) String sortby,
                                 @RequestParam(required = false) String order) {
        return new ResultBean<>(picService.pagePicture(page, perPage, sortby, order));
    }

    /**
     * add Picture setting
     *
     * @return ResultBean
     * @author WangTao
     * @since Created in 2017/11/29 17:33
     */
    @PostMapping(value = "/pictures", consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResultBean<Picture> create(@RequestParam String name,@RequestParam("file") MultipartFile file) throws IOException {
        if (!file.isEmpty()) {
            String origName = file.getOriginalFilename();
            String ext = origName.substring(origName.lastIndexOf("."), origName.length());
            boolean ftpRes = FtpUtils.upload(FtpUtils.Instance.PIC, "/epg/egg", file.getInputStream(), file.getOriginalFilename());
        } else {
            return new ResultBean<>();
        }
        Picture picture = new Picture();
        picture.setName(name);
        /*return new ResultBean<>(picService.createPicture(picture));*/
        return new ResultBean<>();
    }

    /**
     * update Picture setting
     *
     * @param id, PictureRequestVo
     * @return com.epg.act.web.responseVo.ResultBean<com.epg.act.entity.Picture>
     * @author WangTao
     * @since Created in 2017/11/30 14:17
     */
    @PutMapping("/pictures/{id}")
    public ResultBean<?> update(@PathVariable Integer id, @RequestBody PicRequestVo picRequestVo) {
        Picture Picture = picService.updatePicture(id, picRequestVo);
        return new ResultBean<>(Picture == null ? ResultCode.UNKNOW_DATA : Picture);
    }

    /**
     * delete Picture setting
     *
     * @param id 主键
     * @return boolean
     * @author WangTao
     * @since Created in 2017/11/30 16:00
     */
    @DeleteMapping("/pictures/{id}")
    public ResultBean<?> delete(@PathVariable Integer id) {
        return new ResultBean<>(picService.deletePicture(id) ? ResultCode.SUCCESS : ResultCode.UNKNOW_DATA);
    }


    /**
     * TODO description
     *
     * @param id 主键
     * @return com.epg.act.web.responseVo.ResultBean<>
     * @author WangTao
     * @since Created in 2017/11/30 16:04
     */
    @GetMapping("/pictures/{id}")
    public ResultBean<?> get(@PathVariable Integer id) {
        Picture Picture = picService.getPicture(id);
        return new ResultBean<>(Picture == null ? ResultCode.UNKNOW_DATA : Picture);
    }
}
