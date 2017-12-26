package com.epg.act.web.controller.manager;

import com.epg.act.entity.EggPrize;
import com.epg.act.service.PrizeService;
import com.epg.act.web.requestVo.PrizeCreate;
import com.epg.act.web.requestVo.PrizeUpdate;
import com.epg.act.web.requestVo.valid.Create;
import com.epg.act.web.requestVo.valid.Update;
import com.epg.act.web.responseVo.ResultBean;
import com.epg.act.web.responseVo.ResultCode;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@Api(value = "PrizeController", description = "奖品接口")
@RestController
@RequestMapping("/egg")
@Slf4j
public class PrizeController {

    @Resource
    private PrizeService prizeService;

    /**
     * TODO description
     *
     * @param current , size, name, status
     * @return com.epg.act.web.responseVo.ResultBean<com.baomidou.mybatisplus.plugins.Page>
     * @author WangTao
     * @since Created in 2017/11/30 9:51
     */
    @ApiOperation(value = "奖品列表", notes = "奖品列表")
    @GetMapping("/prizes")
    public ResultBean<?> page(@RequestParam(defaultValue = "0") int current,
                              @RequestParam(defaultValue = "20") int size,
                              @RequestParam(required = false) String name,
                              @RequestParam(required = false) Integer status) {
        return new ResultBean<>(prizeService.page(current, size, name, status));
    }

    /**
     * add prize setting
     *
     * @return ResultBean
     * @author WangTao
     * @since Created in 2017/11/29 17:33
     */
    @ApiOperation(value = "创建奖品", notes = "创建奖品规则")
    @PostMapping("/prizes")
    public ResultBean<?> create(@Validated(Create.class) @RequestBody PrizeCreate prize) {
        prizeService.create(prize);
        return new ResultBean<>();
    }

    /**
     * update prize setting
     *
     * @param id 主键, prizeRequestVo
     * @return com.epg.act.web.responseVo.ResultBean<com.epg.act.entity.EggPrize>
     * @author WangTao
     * @since Created in 2017/11/30 14:17
     */
    @ApiOperation(value = "更新奖品", notes = "更新奖品规则")
    @PutMapping("/prizes/{id}")
    public ResultBean<?> update(@PathVariable Integer id, @Validated(Update.class) @RequestBody PrizeUpdate prize) {
        prizeService.update(id, prize);
        return new ResultBean<>();
    }

    /**
     * delete prize setting
     *
     * @param id 主键
     * @return boolean
     * @author WangTao
     * @since Created in 2017/11/30 16:00
     */
    @ApiOperation(value = "删除奖品", notes = "删除奖品规则")
    @DeleteMapping("/prizes/{id}")
    public ResultBean<?> delete(@PathVariable Integer id) {
        prizeService.delete(id);
        return new ResultBean<>();
    }


    /**
     * get prize setting
     *
     * @param id 主键
     * @return com.epg.act.web.responseVo.ResultBean<>
     * @author WangTao
     * @since Created in 2017/11/30 16:04
     */
    @ApiOperation(value = "奖品规则详情", notes = "获取奖品规则详情")
    @GetMapping("/prizes/{id}")
    public ResultBean<?> get(@PathVariable Integer id) {
        EggPrize prize = prizeService.getPrize(id);
        return new ResultBean<>(prize == null ? ResultCode.UNKNOW_DATA : prize);
    }
}
