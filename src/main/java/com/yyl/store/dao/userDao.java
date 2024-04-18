package com.yyl.store.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yyl.store.entity.users;
import org.apache.ibatis.annotations.Param;

/**
 * @author YangYuanLei
 * @ClassName userDao
 * @description: TODO
 * @date 2024年04月02日
 * @version: 1.0
 */
public interface userDao extends BaseMapper<users> {


    users select(@Param("name") String name);

    int inserts(@Param("req") users userEnrollEnrollSave);
}
