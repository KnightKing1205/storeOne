package com.yyl.store.entity;

import lombok.Data;

import java.util.Date;

/**
 * @author 65199
 * @ClassName Result
 * @description: TODO
 * @date 2024年04月18日
 * @version: 1.0
 */
@Data
public class Result {
    private Integer code;
    private boolean success;
    private String message;
    private Object data;
    private Date date;

    public static Result loginBody(Integer code,
                                   Boolean success,
                                   String message,
                                   Object data,
                                   Date date
    ){
        Result result = new Result();
        result.setCode(code);
        result.setSuccess(success);
        result.setData(data);
        result.setMessage(message);
        result.setDate(date);

        return  result;
    }

    public static Result enroll(Integer code,
                                Boolean success,
                                String message,
                                String data,
                                Date date){

        Result result = new Result();
        result.setCode(code);
        result.setSuccess(success);
        result.setData(data);
        result.setMessage(message);
        result.setDate(date);
        return  result;
    }


}
