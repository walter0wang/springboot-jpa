package com.epg.act.web.controller.manager;

import com.epg.act.entity.EggActiv;
import com.epg.act.entity.QEggActiv;
import com.epg.act.entity.QEggPrize;
import com.epg.act.repository.ActivityRepository;
import com.epg.act.service.ActivityService;
import com.epg.act.web.requestVo.ActivityRequestVo;
import com.epg.act.web.responseVo.ResultBean;
import com.epg.act.web.responseVo.ResultCode;
import com.querydsl.jpa.impl.JPAQueryFactory;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.validation.Valid;

@Api(value = "ActivityController", description = "活动接口")
@RestController
@RequestMapping("/egg")
@Slf4j
public class ActivityController {

    @Resource
    private ActivityService activityService;

    @Resource
    private ActivityRepository activityRepository;

    /**
     * page prize
     *
     * @param page, perPage, sortby, order
     * @return com.epg.act.web.responseVo.ResultBean<org.springframework.data.domain.Page>
     * @author WangTao
     * @since Created in 2017/11/30 9:51
     */
    @ApiOperation(value = "活动列表", notes = "活动列表")
    @GetMapping("/activ")
    public ResultBean<Page> page(@RequestParam(defaultValue = "1") int page,
                                 @RequestParam(defaultValue = "20") int perPage,
                                 @RequestParam(required = false) String sortby,
                                 @RequestParam(required = false) String order) {
        return new ResultBean<>(activityService.pageActivity(page, perPage, sortby, order));
    }

    /**
     * add prize setting
     *
     * @return ResultBean
     * @author WangTao
     * @since Created in 2017/11/29 17:33
     */
    @ApiOperation(value = "创建活动", notes = "根据prize对象创建活动")
    @PostMapping("/activ")
    public ResultBean<EggActiv> create(@Valid @RequestBody ActivityRequestVo activity) {
        return new ResultBean<>(activityService.createActivity(activity));
    }

    /**
     * update prize setting
     *
     * @param id, prizeRequestVo
     * @return com.epg.act.web.responseVo.ResultBean<com.epg.act.entity.EggPrize>
     * @author WangTao
     * @since Created in 2017/11/30 14:17
     */
    @PutMapping("/activ/{id}")
    public ResultBean<?> update(@PathVariable Integer id, @RequestBody ActivityRequestVo activity) {
        EggActiv eggActiv = activityService.updateActivity(id, activity);
        return new ResultBean<>(eggActiv == null ? ResultCode.UNKNOW_DATA : eggActiv);
    }

    /**
     * delete prize setting
     *
     * @param id
     * @return boolean
     * @author WangTao
     * @since Created in 2017/11/30 16:00
     */
    @DeleteMapping("/activ/{id}")
    public ResultBean<?> delete(@PathVariable Integer id) {
        return new ResultBean<>(activityService.deleteActivity(id) ? ResultCode.SUCCESS : ResultCode.UNKNOW_DATA);
    }


    /**
     * get prize setting
     *
     * @param id 主键
     * @return com.epg.act.web.responseVo.ResultBean<>
     * @author WangTao
     * @since Created in 2017/11/30 16:04
     */
    @GetMapping("/activ/{id}")
    public ResultBean<?> get(@PathVariable Integer id) {
        EggActiv activity = activityService.getActivity(id);
        return new ResultBean<>(activity == null ? ResultCode.UNKNOW_DATA : activity);
    }


    @PersistenceContext
    private EntityManager entityManager;
    //查询工厂实体
    private JPAQueryFactory queryFactory;

    //实例化控制器完成后执行该方法实例化JPAQueryFactory
    @PostConstruct
    public void initFactory() {
        System.out.println("开始实例化JPAQueryFactory");
        queryFactory = new JPAQueryFactory(entityManager);
    }

    @GetMapping("/test/{id}")
    public ResultBean<?> test(@PathVariable Integer id) {
        QEggActiv _eggActiv = QEggActiv.eggActiv;
        QEggPrize _eggPrize = QEggPrize.eggPrize;
        return new ResultBean<>(queryFactory.select(_eggActiv).from(_eggActiv).leftJoin(_eggPrize)
                .on(_eggActiv.prizeTplId.intValue().eq(_eggPrize.id.intValue())).where(_eggActiv.id.intValue().eq(id)).fetch());
    }
}
