package com.yyl.store.entity.req;

import com.alibaba.excel.annotation.ExcelProperty;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author 65199
 * @ClassName buyReq
 * @description: TODO
 * @date 2024年04月06日
 * @version: 1.0
 */
@Data
public class buyReq extends accountReq{

    @ApiModelProperty(value = "商品名称")
    @ExcelProperty(value = "商品名称")
    private String goodsName;

    @ApiModelProperty(value = "商品数量")
    @ExcelProperty(value = "商品数量")
    private Integer goodsNum;
}
