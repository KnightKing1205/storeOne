package com.yyl.store.entity;

import com.alibaba.excel.annotation.ExcelProperty;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;

/**
 * @author 65199
 * @ClassName goods
 * @description: TODO
 * @date 2024年04月05日
 * @version: 1.0
 */
@Data
public class goods {
    @ApiModelProperty(value = "id")
    @ExcelProperty(value = "id")
    private  Integer id;

    @ApiModelProperty(value = "商品名称")
    @ExcelProperty(value = "商品名称")
    private String goodsName;

    @ApiModelProperty(value = "商品价格")
    @ExcelProperty(value = "商品价格")
    private BigDecimal goodsPrice;

    @ApiModelProperty(value = "商品数量")
    @ExcelProperty(value = "商品数量")
    private Integer goodsNum;

    @ApiModelProperty(value = "商品状态")
    @ExcelProperty(value = "商品状态")
    private Integer goodsState;

    @ApiModelProperty(value = "商品折扣")
    @ExcelProperty(value = "商品折扣")
    private BigDecimal goodsDiscount;

    @ApiModelProperty(value = "商品发送地")
    @ExcelProperty(value = "商品发送地")
    private String goodsAddress;

    @ApiModelProperty(value = "商品供货商")
    @ExcelProperty(value = "商品供货商")
    private Integer goodsProvide;
}
