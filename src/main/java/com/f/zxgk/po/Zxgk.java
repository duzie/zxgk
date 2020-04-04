package com.f.zxgk.po;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("zxgk")
public class Zxgk {

    @TableId
    private int id;

    private String name;

    private String idnumber;
}
