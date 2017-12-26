package com.epg.act.web.controller.manager;

import com.epg.act.entity.Picture;
import com.epg.act.service.PicService;
import com.epg.act.web.responseVo.ResultBean;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
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
@Api(value = "PicController", description = "图片接口")
@RestController
@RequestMapping("/egg")
@Slf4j
public class PicController {

    @Resource
    private PicService picService;

    /**
     * picture page data
     *
     * @param current, size, name
     * @return com.epg.act.web.responseVo.ResultBean<org.springframework.data.domain.Page>
     * @author WangTao
     * @since Created in 2017-12-21 14:35:26
     */
    @ApiOperation(value = "图片列表", notes = "图片列表")
    @GetMapping("/pictures")
    public ResultBean<?> page(@RequestParam(defaultValue = "0") int current,
                              @RequestParam(defaultValue = "20") int size,
                              @RequestParam(required = false) String name) {
        return new ResultBean<>(picService.page(current, size, name));
    }

    /**
     * upload Picture
     *
     * @return ResultBean
     * @author WangTao
     * @since Created in 2017-12-21 14:35:35
     */
    @ApiOperation(value = "上传图片", notes = "上传图片")
    @PostMapping(value = "/pictures", consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResultBean<Picture> create(@RequestParam String createUser, @RequestParam("file") MultipartFile file) throws IOException {
        picService.create(file, createUser);
        return new ResultBean<>();
    }

    /**
     * delete Picture
     *
     * @param id 主键
     * @return boolean
     * @author WangTao
     * @since Created in 2017-12-21 14:35:59
     */
//    @DeleteMapping("/pictures/{id}")
    public ResultBean<?> delete(@PathVariable Long id) {
        picService.delete(id);
        return new ResultBean<>();
    }


    /**
     * get picture
     *
     * @param id 主键
     * @return com.epg.act.web.responseVo.ResultBean<>
     * @author WangTao
     * @since Created in 2017-12-21 14:36:04
     */
    @ApiOperation(value = "获取图片信息", notes = "获取图片信息")
    @GetMapping("/pictures/{id}")
    public ResultBean<?> get(@PathVariable Long id) {
        return new ResultBean<>(picService.get(id));
    }
}
