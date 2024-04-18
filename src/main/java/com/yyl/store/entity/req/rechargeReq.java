package com.yyl.store.entity.req;

import com.alibaba.excel.annotation.ExcelProperty;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;

/**
 * @author 65199
 * @ClassName rechargeReq
 * @description: TODO
 * @date 2024年04月05日
 * @version: 1.0
 */
@Data
public class rechargeReq extends accountReq {
    @ApiModelProperty(value = "充值金额")
    @ExcelProperty(value = "充值金额")
    private BigDecimal money;
}
