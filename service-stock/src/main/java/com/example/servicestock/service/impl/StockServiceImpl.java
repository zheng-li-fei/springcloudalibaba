package com.example.servicestock.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.example.common.exception.BizException;
import com.example.servicestock.mapper.StockDao;
import com.example.servicestock.mapper.entity.StockDO;
import com.example.servicestock.service.StockService;
import com.example.servicestock.service.bo.StockReqBO;
import com.example.servicestock.service.bo.StockResBO;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @description:
 * @author: zhenglifei
 * @create: 2022/4/19 11:46
 **/
@Slf4j
@Service
public class StockServiceImpl implements StockService {

    @Autowired
    StockDao stockDao;

    @Override
    public void addStock(StockReqBO stockReqBO) {
        if (stockReqBO == null || StringUtils.isBlank(stockReqBO.getGoodsId())) {
            throw new BizException("新增库存操作参数异常");
        }
        StockDO stockDO = StockDO.builder().build();
        BeanUtil.copyProperties(stockReqBO, stockDO);

        Integer integer = stockDao.addStock(stockDO);
        if (integer <= 0) {
            throw new BizException("库存新增异常");
        }
    }

    @Override
    public void reduceStock(StockReqBO stockReqBO) {
        if (stockReqBO == null || StringUtils.isBlank(stockReqBO.getGoodsId())
                || stockReqBO.getOperatorStockNum() == null) {
            throw new BizException("减少库存操作参数异常");
        }
        StockDO stockDO = StockDO.builder().build();
        BeanUtil.copyProperties(stockReqBO, stockDO);

        Integer integer = stockDao.reduceStock(stockDO);
        if (integer <= 0) {
            throw new BizException("库存不足");
        }
    }

    @Override
    public StockResBO existGoods(String goodsId) {
        if (StringUtils.isBlank(goodsId)) {
            throw new BizException("商品信息不存在");
        }
        StockDO stockDO = stockDao.selectOne(new LambdaUpdateWrapper<StockDO>().eq(StockDO::getGoodsId, goodsId));
        StockResBO stockResBO = StockResBO.builder().build();
        if (stockDO != null) {
            BeanUtil.copyProperties(stockDO, stockResBO);
            return stockResBO;
        } else {
            return stockResBO;
        }
    }
}
