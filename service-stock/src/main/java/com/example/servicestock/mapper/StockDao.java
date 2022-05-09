package com.example.servicestock.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.servicestock.mapper.entity.StockDO;
import com.example.servicestock.service.bo.StockReqBO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * @description:
 * @author: zhenglifei
 * @create: 2022/4/19 11:47
 **/
@Repository
public interface StockDao extends BaseMapper<StockDO> {

    boolean addStock(@Param(value = "stockDO") StockDO stockDO);

    boolean reduceStock(@Param(value = "stockDO") StockDO stockDO);
}
