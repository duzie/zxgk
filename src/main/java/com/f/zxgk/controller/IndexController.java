package com.f.zxgk.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.f.zxgk.po.Zxgk;
import com.f.zxgk.service.ZxgkService;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class IndexController {


    @Autowired
    private ZxgkService zxgkService;

    @PostMapping("search")
    @ResponseBody
    public List<Zxgk> search(Zxgk zxgk) {
        return zxgkService.lambdaQuery().like(Zxgk::getName, zxgk.getName())
                .page(new Page<Zxgk>(1, 3)).getRecords();
    }

    @PostMapping("s")
    @ResponseBody
    public List<Zxgk> searchJson(@RequestBody Zxgk zxgk) {
        return zxgkService.lambdaQuery().like(Zxgk::getName, zxgk.getName())
                .page(new Page<Zxgk>(1, 3)).getRecords();
    }

    @GetMapping("check")
    public String index() {
        Zxgk one = zxgkService.lambdaQuery().orderByDesc(Zxgk::getId).last("limit 1").one();
        String s = "已收录数：" + zxgkService.count();
        s += "<br>最后收录时间：" + DateFormatUtils.format(one.getCreateDate(), "yyyy-MM-dd HH:mm");
        s += "<br>" + one.getName() + " " + one.getIdnumber() + " " + one.getRemark();
        return s;
    }
}
