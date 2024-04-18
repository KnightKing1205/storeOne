package com.yyl.store.entity.req;

import com.alibaba.excel.annotation.ExcelProperty;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author 65199
 * @ClassName goodsName
 * @description: TODO
 * @date 2024年04月06日
 * @version: 1.0
 */
@Data
public class goodsNameReq extends accountReq{

    @ApiModelProperty(value = "商品名称")
    @ExcelProperty(value = "商品名称")
    private String goodsName;
}
