package com.epg.act.web.controller.manager;

import com.epg.act.entity.EggPrize;
import com.epg.act.service.PrizeService;
import com.epg.act.web.requestVo.PrizeRequestVo;
import com.epg.act.web.responseVo.ResultBean;
import com.epg.act.web.responseVo.ResultCode;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;

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
     * @param page, perPage, sortby, order
     * @return com.epg.act.web.responseVo.ResultBean<org.springframework.data.domain.Page>
     * @author WangTao
     * @since Created in 2017/11/30 9:51
     */
    @ApiOperation(value = "奖品列表", notes = "奖品列表")
    @GetMapping("/prizes")
    public ResultBean<Page> page(@RequestParam(defaultValue = "1") int page,
                                 @RequestParam(defaultValue = "20") int perPage,
                                 @RequestParam(required = false) String sortby,
                                 @RequestParam(required = false) String order) {
        return new ResultBean<>(prizeService.pagePrize(page, perPage, sortby, order));
    }

    /**
     * add prize setting
     *
     * @return ResultBean
     * @author WangTao
     * @since Created in 2017/11/29 17:33
     */
    @ApiOperation(value = "创建奖品", notes = "根据prize对象创建奖品")
    @PostMapping("/prizes")
    public ResultBean<EggPrize> create(@Valid @RequestBody PrizeRequestVo prize) {
        return new ResultBean<>(prizeService.createPrize(prize));
    }

    /**
     * update prize setting
     *
     * @param [id, prizeRequestVo]
     * @return com.epg.act.web.responseVo.ResultBean<com.epg.act.entity.EggPrize>
     * @author WangTao
     * @since Created in 2017/11/30 14:17
     */
    @PutMapping("/prizes/{id}")
    public ResultBean<?> update(@PathVariable Integer id, @RequestBody PrizeRequestVo prize) {
        EggPrize eggPrize = prizeService.updatePrize(id, prize);
        return new ResultBean<>(eggPrize == null ? ResultCode.UNKNOW_DATA : eggPrize);
    }

    /**
     * delete prize setting
     *
     * @param id
     * @return boolean
     * @author WangTao
     * @since Created in 2017/11/30 16:00
     */
    @DeleteMapping("/prizes/{id}")
    public ResultBean<?> delete(@PathVariable Integer id) {
        return new ResultBean<>(prizeService.deletePrize(id) ? ResultCode.SUCCESS : ResultCode.UNKNOW_DATA);
    }


    /**
     * TODO description
     *
     * @param id 主键
     * @return com.epg.act.web.responseVo.ResultBean<>
     * @author WangTao
     * @since Created in 2017/11/30 16:04
     */
    @GetMapping("/prizes/{id}")
    public ResultBean<?> get(@PathVariable Integer id) {
        EggPrize prize = prizeService.getPrize(id);
        return new ResultBean<>(prize == null ? ResultCode.UNKNOW_DATA : prize);
    }
}
