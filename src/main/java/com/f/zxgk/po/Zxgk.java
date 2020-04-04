package com.f.zxgk.po;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

@Data
@TableName("zxgk")
public class Zxgk {

    @TableId
    private int id;

    private String name;

    private String idnumber;

    private Date createDate;

}
