package com.zlf.serverstock.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zlf.serverstock.dao.entity.StockDO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * @description:
 * @author: zhenglifei
 * @create: 2022/4/19 11:47
 **/
@Repository
public interface StockDao extends BaseMapper<StockDO> {

    Integer addStock(@Param(value = "stockDO") StockDO stockDO);

    Integer reduceStock(@Param(value = "stockDO") StockDO stockDO);
}
