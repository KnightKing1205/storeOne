package com.yyl.store.service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yyl.store.dao.userDao;
import com.yyl.store.entity.users;
import com.yyl.store.service.ComFoundException;
import com.yyl.store.service.QueryTokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author 65199
 * @ClassName QueryTokenServiceImpl
 * @description: TODO
 * @date 2024年04月18日
 * @version: 1.0
 */
@Service
public class QueryTokenServiceImpl extends ServiceImpl<userDao, users> implements QueryTokenService{
    @Autowired
    private userDao userDao;

    @Override
    public Boolean returnQueryToken(String token){
        QueryWrapper<users> qw=new QueryWrapper<>();
        qw.eq("token",token);
        List<users> userList=userDao.selectList(qw);
        try{
            if (userList==null||userList.size()==0){
                return false;
            }
        }catch (NullPointerException e){
            throw new ComFoundException();
        }
        return true;
    }
}
