package com.yyl.store.entity.req;

import com.alibaba.excel.annotation.ExcelProperty;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author 65199
 * @ClassName accountReq
 * @description: TODO
 * @date 2024年04月05日
 * @version: 1.0
 */
@Data
public class accountReq {
    @ApiModelProperty(value = "用户账号")
    @ExcelProperty(value = "用户账号")
    private String useAccount;

    @ApiModelProperty(value = "用户密码")
    @ExcelProperty(value = "用户密码")
    private String usePassword;
}
