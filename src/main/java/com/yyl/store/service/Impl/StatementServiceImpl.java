package com.yyl.store.service.Impl;

import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yyl.store.dao.StatementDao;
import com.yyl.store.entity.statement;
import com.yyl.store.service.StatementService;
import org.springframework.stereotype.Service;

/**
 * @author 65199
 * @ClassName SatementServiceImpl
 * @description: TODO
 * @date 2024年04月23日
 * @version: 1.0
 */
@Service
public class StatementServiceImpl extends ServiceImpl<StatementDao, statement> implements StatementService {
}
