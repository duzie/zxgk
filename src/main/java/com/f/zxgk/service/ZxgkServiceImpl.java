package com.f.zxgk.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.f.zxgk.mapper.ZxgkMapper;
import com.f.zxgk.po.Zxgk;
import org.springframework.stereotype.Service;

@Service
public class ZxgkServiceImpl extends ServiceImpl<ZxgkMapper, Zxgk> implements ZxgkService {

    @Override
    public void deleteRe() {
        this.baseMapper.deleteRe();
    }
}
