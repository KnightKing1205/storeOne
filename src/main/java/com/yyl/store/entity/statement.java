package com.yyl.store.entity;

import com.alibaba.excel.annotation.ExcelProperty;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author 65199
 * @ClassName statement
 * @description: TODO
 * @date 2024年04月05日
 * @version: 1.0
 */
@Data
public class statement {
    @ApiModelProperty(value = "id")
    @ExcelProperty(value = "id")
    private Integer id;

    @ApiModelProperty(value = "购买者姓名")
    @ExcelProperty(value = "购买者姓名")
    private String buyName;

    @ApiModelProperty(value = "购买者账号")
    @ExcelProperty(value = "购买者账号")
    private String buyAccount;

    @ApiModelProperty(value = "购买者地址")
    @ExcelProperty(value = "购买者地址")
    private String buyAddress;

    @ApiModelProperty(value = "购买的商品名称")
    @ExcelProperty(value = "购买的商品名称")
    private String shopName;

    @ApiModelProperty(value = "购买数量")
    @ExcelProperty(value = "购买数量")
    private Integer shopNum;

    @ApiModelProperty(value = "供货商名称")
    @ExcelProperty(value = "供货商名称")
    private String sendName;

    @ApiModelProperty(value = "供货商地址")
    @ExcelProperty(value = "供货商地址")
    private String sendAccount;

    @ApiModelProperty(value = "商品发送地")
    @ExcelProperty(value = "商品发送地")
    private String sendAddress;

    @ApiModelProperty(value = "订单总价")
    @ExcelProperty(value = "订单总价")
    private BigDecimal money;

    @ApiModelProperty("创建时间")
    private Date createDate;

    @ApiModelProperty(value = "备注")
    @ExcelProperty(value = "备注")
    private String remark;
}
