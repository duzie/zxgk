package com.f.zxgk.controller;

import com.f.zxgk.po.Zxgk;
import com.f.zxgk.service.ZxgkService;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class IndexController {

    @Autowired
    private ZxgkService zxgkService;

    @GetMapping
    public String index() {
        Zxgk one = zxgkService.lambdaQuery().orderByDesc(Zxgk::getId).last("limit 1").one();
        String s = "已收录数：" + zxgkService.count();
        s += "<br>最后收录时间：" + DateFormatUtils.format(one.getCreateDate(), "yyyy-MM-dd HH:mm");
        s += "<br>" + one.getName() + " " + one.getIdnumber();
        return s;
    }
}
