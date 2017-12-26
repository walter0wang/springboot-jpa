package com.epg.act.web.controller.manager;

import com.epg.act.service.PicTplService;
import com.epg.act.web.requestVo.PicTplCreate;
import com.epg.act.web.requestVo.PicTplUpdate;
import com.epg.act.web.requestVo.valid.Create;
import com.epg.act.web.requestVo.valid.Update;
import com.epg.act.web.responseVo.ResultBean;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

;

/**
 * egg picture manager
 *
 * @author WANGTAO
 * @since 2017年12月21日14:36:45
 */
@Api(value = "PicTplController", description = "金蛋图片模板接口")
@RestController
@RequestMapping("/egg")
@Slf4j
public class PicTplController {

    @Resource
    private PicTplService picTplService;

    /**
     * TODO description
     *
     * @param current page index
     * @param size    page size
     * @return com.epg.act.web.responseVo.ResultBean<org.springframework.data.domain.Page>
     * @author WangTao
     * @since Created in 2017-12-21 14:35:26
     */
    @ApiOperation(value = "图片模板列表", notes = "图片模板列表")
    @GetMapping("/picTpl")
    public ResultBean<?> page(@RequestParam(defaultValue = "0") int current,
                              @RequestParam(defaultValue = "20") int size,
                              @RequestParam(required = false) String name,
                              @RequestParam(required = false) Integer status) {
        return new ResultBean<>(picTplService.page(current, size, name, status));
    }

    /**
     * upload Picture tpl
     *
     * @return ResultBean
     * @author WangTao
     * @since Created in 2017-12-21 14:35:35
     */
    @ApiOperation(value = "创建图片模板", notes = "创建图片模板")
    @PostMapping("/picTpl")
    public ResultBean<?> create(@Validated(Create.class) @RequestBody PicTplCreate requestVo) {
        picTplService.create(requestVo);
        return new ResultBean<>();
    }

    /**
     * update Picture tpl
     *
     * @param id, PictureRequestVo
     * @return com.epg.act.web.responseVo.ResultBean<>
     * @author WangTao
     * @since Created in 2017-12-25 09:59:16
     */
    @ApiOperation(value = "更新图片模板", notes = "更新图片模板")
    @PutMapping("/picTpl/{id}")
    public ResultBean<?> update(@PathVariable Integer id, @Validated(Update.class) @RequestBody PicTplUpdate picRequestVo) {
        picTplService.update(id, picRequestVo);
        return new ResultBean<>();
    }

    /**
     * delete Picture tpl
     *
     * @param id 主键
     * @return boolean
     * @author WangTao
     * @since Created in 2017-12-25 09:59:12
     */
    @ApiOperation(value = "删除图片模板", notes = "删除图片模板")
    @DeleteMapping("/picTpl/{id}")
    public ResultBean<?> delete(@PathVariable Integer id) {
        picTplService.delete(id);
        return new ResultBean<>();
    }


    /**
     * get picture tpl
     *
     * @param id 主键
     * @return com.epg.act.web.responseVo.ResultBean<>
     * @author WangTao
     * @since Created in 2017-12-21 14:36:04
     */
    @ApiOperation(value = "图片模板详情", notes = "图片模板详情")
    @GetMapping("/picTpl/{id}")
    public ResultBean<?> get(@PathVariable Integer id) {
        return new ResultBean<>(picTplService.get(id));
    }
}
