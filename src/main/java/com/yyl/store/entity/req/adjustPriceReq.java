package com.yyl.store.entity.req;

import com.alibaba.excel.annotation.ExcelProperty;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;

/**
 * @author 65199
 * @ClassName adjustPriceReq
 * @description: TODO
 * @date 2024年04月06日
 * @version: 1.0
 */
@Data
public class adjustPriceReq extends accountReq{
    @ApiModelProperty(value = "商品名称")
    @ExcelProperty(value = "商品名称")
    private String goodsName;

    @ApiModelProperty(value = "商品价格")
    @ExcelProperty(value = "商品价格")
    private BigDecimal goodsPrice;
}
