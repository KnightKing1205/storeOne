package com.yyl.store.entity;

import com.alibaba.excel.annotation.ExcelProperty;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;

/**
 * @author YangYuanLei
 * @ClassName users
 * @description: TODO
 * @date 2024年04月02日
 * @version: 1.0
 */
@TableName(value = "user")
@Data
public class users {

    @ApiModelProperty(value = "id")
    @TableField(value = "id")
    private Integer id;

    @ApiModelProperty(value = "用户名称")
    @ExcelProperty(value = "用户名称")
    @TableId
    private String useName;

    @ApiModelProperty(value = "用户账号")
    @TableField(value = "use_account")
    private String useAccount;

    @ApiModelProperty(value = "用户密码")
    @TableField(value = "use_password")
    private String usePassword;

    @ApiModelProperty(value = "用户等级")
    @TableField(value = "use_level")
    private Integer useLevel;

    @ApiModelProperty(value = "用户余额")
    @TableField(value = "use_balance")
    private BigDecimal useBalance;

    @ApiModelProperty(value = "用户地址")
    @TableField(value = "use_address")
    private String useAddress;

    @ApiModelProperty(value = "头部")
    @TableField(value = "token")
    private String token;
}
