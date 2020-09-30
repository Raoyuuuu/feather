package com.example.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * @author Administrator
 * @Title:
 * @auther: raohr
 * @Date: 2020/9/30 16:28
 * @param:
 * @Description:
 * @return:
 * @throws:
 */
@Data
@TableName("user")
public class User implements Serializable {

    @TableId
    private String id;

    @TableField("name")
    private String name;

    @TableField("password")
    private String password;
}
