package com.yyl.store.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.yyl.store.entity.users;

/**
 * @author 65199
 */
public interface QueryTokenService extends IService<users> {
    Boolean returnQueryToken(String token);
}
