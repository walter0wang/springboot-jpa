package com.epg.act.web.controller.manager;

import com.epg.act.entity.EggActiv;
import com.epg.act.service.ActivityService;
import com.epg.act.util.Pager;
import com.epg.act.web.requestVo.ActivCreate;
import com.epg.act.web.requestVo.ActivUpdate;
import com.epg.act.web.responseVo.ResultBean;
import com.epg.act.web.responseVo.ResultCode;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;

@Api(value = "ActivityController", description = "活动接口")
@RestController
@RequestMapping("/egg")
@Slf4j
public class ActivityController {

    @Resource
    private ActivityService activityService;

    /**
     * page prize
     *
     * @param current, size, name, status
     * @return com.epg.act.web.responseVo.ResultBean<org.springframework.data.domain.Page>
     * @author WangTao
     * @since Created in 2017/11/30 9:51
     */
    @ApiOperation(value = "活动列表", notes = "活动列表")
    @GetMapping("/activ")
    public ResultBean<Pager<EggActiv>> page(@RequestParam(defaultValue = "0") int current,
                              @RequestParam(defaultValue = "20") int size,
                              @RequestParam(required = false) String name,
                              @RequestParam(required = false) Integer status) {
        return new ResultBean<>(activityService.page(current, size, name, status));
    }

    /**
     * add activ
     *
     * @return ResultBean
     * @author WangTao
     * @since Created in 2017/11/29 17:33
     */
    @ApiOperation(value = "创建活动", notes = "创建活动")
    @PostMapping("/activ")
    public ResultBean<?> create(@Valid @RequestBody ActivCreate activity) {
        activityService.createActivity(activity);
        return new ResultBean<>();
    }

    /**
     * update activ
     *
     * @param id, prizeRequestVo
     * @return com.epg.act.web.responseVo.ResultBean<>
     * @author WangTao
     * @since Created in 2017/11/30 14:17
     */
    @ApiOperation(value = "更新活动", notes = "更新活动，包含更新活动状态")
    @PutMapping("/activ/{id}")
    public ResultBean<?> update(@PathVariable Integer id,@Valid @RequestBody ActivUpdate activity) {
        activityService.updateActivity(id, activity);
        return new ResultBean<>();
    }

    /**
     * delete activ
     *
     * @param id
     * @return boolean
     * @author WangTao
     * @since Created in 2017/11/30 16:00
     */
    @ApiOperation(value = "删除活动", notes = "删除活动")
    @DeleteMapping("/activ/{id}")
    public ResultBean<?> delete(@PathVariable Integer id) {
        activityService.deleteActivity(id);
        return new ResultBean<>();
    }


    /**
     * get activ
     *
     * @param id 主键
     * @return com.epg.act.web.responseVo.ResultBean<>
     * @author WangTao
     * @since Created in 2017/11/30 16:04
     */
    @ApiOperation(value = "活动详情", notes = "活动详情")
    @GetMapping("/activ/{id}")
    public ResultBean<?> get(@PathVariable Integer id) {
        EggActiv activity = activityService.getActivity(id);
        return new ResultBean<>(activity == null ? ResultCode.UNKNOW_DATA : activity);
    }

}
