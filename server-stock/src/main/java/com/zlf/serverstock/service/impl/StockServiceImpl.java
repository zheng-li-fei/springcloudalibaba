package com.zlf.serverstock.service.impl;


import cn.hutool.core.bean.BeanUtil;
import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.zlf.api.commonapistock.bo.StockReqBO;
import com.zlf.api.commonapistock.bo.StockResBO;
import com.zlf.commonbase.exception.BizException;
import com.zlf.serverstock.config.sentinel.StockBlockHandler;
import com.zlf.serverstock.config.sentinel.StockFallbackClass;
import com.zlf.serverstock.dao.StockDao;
import com.zlf.serverstock.dao.entity.StockDO;
import com.zlf.serverstock.service.StockService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
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
    @SentinelResource(value = "addStock",
            blockHandler = "addStockBlockHandler", blockHandlerClass = StockBlockHandler.class,
            fallback = "addStockFallback", fallbackClass = StockFallbackClass.class, exceptionsToIgnore = {BizException.class})
    public boolean addStock(StockReqBO stockReqBO) {
        if (stockReqBO == null || StringUtils.isBlank(stockReqBO.getGoodsId())) {
            throw new BizException("新增库存操作参数异常");
        }
        StockDO stockDO = StockDO.builder().build();
        BeanUtil.copyProperties(stockReqBO, stockDO);

        Integer integer = stockDao.addStock(stockDO);
        if (integer <= 0) {
            throw new BizException("库存新增异常");
        }
        return true;
    }

    @Override
    @SentinelResource(value = "reduceStock",
            blockHandler = "reduceStockBlockHandler", blockHandlerClass = StockBlockHandler.class,
            fallback = "reduceStockFallback", fallbackClass = StockFallbackClass.class, exceptionsToIgnore = {BizException.class})
    public boolean reduceStock(StockReqBO stockReqBO) {
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
        return true;
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
