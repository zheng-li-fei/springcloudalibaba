package com.example.serviceorder.servicefeign;

import com.example.common.service.log.SysLog;
import com.example.common.exception.BizException;
import com.example.serviceorder.servicefeign.stock.feignVO.StockReqVO;
import com.example.serviceorder.servicefeign.stock.feignVO.StockResBO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @description:
 * @author: zhenglifei
 * @create: 2022/4/20 9:17
 **/
@Slf4j
@Service
public class RemoteServiceFeignFallback implements RemoteServiceFeign {

    @Override
    public void addStock(StockReqVO stockReqVO) {
        throw new BizException("添加库存方法,触发服务降级");
    }

    @Override
    public void reduceStock(StockReqVO stockReqVO) {
        throw new BizException("减少库存方法,触发服务降级");
    }

    @Override
    public StockResBO existGoods(String goodsId) {
        throw new BizException("判断商品是否存在方法,触发服务降级");
    }

    @Override
    public Boolean saveLog(SysLog sysLog) {
        log.error("日志服务降级了,sysLog {}", sysLog);
        return false;
    }
}
