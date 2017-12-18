package com.epg.act.web.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@Api(value = "testController", description = "测试接口")
@RestController
@Slf4j
public class TestController {

    @ApiOperation(value = "测试1", notes = "测试1接口")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "username", value = "用户名", required = true, dataType = "string"),
            @ApiImplicitParam(name = "passwd", value = "密码", required = true, dataType = "string")
    })
    @RequestMapping(value = "/Test", method = {RequestMethod.GET})
    public String Test() {
        return "hello";
    }

}
