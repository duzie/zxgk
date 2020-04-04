package com.f.zxgk.controller;

import com.f.zxgk.service.ZxgkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class IndexController {

    @Autowired
    private ZxgkService zxgkService;

    @GetMapping
    public String index() {
        return ".已收录数：" + zxgkService.count();
    }
}
