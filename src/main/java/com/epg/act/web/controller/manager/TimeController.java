package com.epg.act.web.controller.manager;

import com.epg.act.service.TimeService;
import com.epg.act.web.exception.CheckException;
import com.epg.act.web.requestVo.TimeCreate;
import com.epg.act.web.requestVo.TimeUpdate;
import com.epg.act.web.responseVo.ResultBean;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;

@Api(value = "TimeController", description = "时间点接口")
@RestController
@RequestMapping("/egg")
@Slf4j
public class TimeController {

    @Resource
    private TimeService timeService;

    /**
     * page
     *
     * @param current 当前
     * @param size 每页多少
     * @return com.epg.act.web.responseVo.ResultBean<com.epg.act.util.Pager>
     * @author WangTao
     * @since Created in 2017-12-21 11:26:59
     */
    @ApiOperation(value = "时间点列表", notes = "时间点列表")
    @GetMapping("/time")
    public ResultBean<?> page(@RequestParam(defaultValue = "0") int current,
                              @RequestParam(defaultValue = "20") int size) {
        return new ResultBean<>(timeService.page(current, size));
    }

    /**
     * add  setting
     *
     * @return ResultBean
     * @author WangTao
     * @since Created in 2017-12-21 11:26:51
     */
    @ApiOperation(value = "生成活动时点", notes = "根据活动规则 生成活动时点")
    @PostMapping("/time")
    public ResultBean<?> create(@Valid @RequestBody TimeCreate requestVo) {
        return new ResultBean<>(timeService.create(requestVo));
    }

    /**
     *
     * @param id eggTime id
     * @param requestVo egg time message body to update
     * @since Create in 2017-12-21 11:03:31
     * @return com.epg.act.web.responseVo.ResultBean<>
     */
    @ApiOperation(value = "编辑活动时点", notes = "编辑活动时点")
    @PutMapping("/time/{id}")
    public ResultBean<?> update(@PathVariable Long id,@Valid @RequestBody TimeUpdate requestVo) {
        timeService.update(id, requestVo);
        return new ResultBean<>();
    }

    /**
     * delete  setting
     *
     * @param id 主键
     * @return boolean
     * @author WangTao
     * @since Created in 2017-12-21 11:27:08
     */
    @ApiOperation(value = "删除活动时点", notes = "删除活动时点")
    @DeleteMapping("/time/{id}")
    public ResultBean<?> delete(@PathVariable Long id) {
        timeService.delete(id);
        return new ResultBean<>();
    }


    /**
     * get  setting
     *
     * @param id 主键
     * @return com.epg.act.web.responseVo.ResultBean<>
     * @author WangTao
     * @since Created in 2017-12-21 11:27:12
     */
    @ApiOperation(value = "活动时点详情", notes = "活动时点详情")
    @GetMapping("/time/{id}")
    public ResultBean<?> get(@PathVariable Long id) throws CheckException {
        return new ResultBean<>(timeService.get(id));
    }
}
