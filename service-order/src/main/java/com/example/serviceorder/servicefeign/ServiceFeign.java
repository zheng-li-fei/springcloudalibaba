package com.example.serviceorder.servicefeign;

import com.example.common.config.log.event.SysLog;
import com.example.serviceorder.config.OpenFeignConfig;
import com.example.serviceorder.servicefeign.stock.feignVO.StockReqVO;
import com.example.serviceorder.servicefeign.stock.feignVO.StockResBO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

/**
 * @description: 调用网关 - 远程的库存服务
 * @author: zhenglifei
 * @create: 2022/4/19 15:16
 * <p>
 * name:指定调用rest接口所对应的服务名 (相同服务名只能存在一个接口)
 **/
@FeignClient(value = "gateway", fallback = ServiceFeignFallback.class, configuration = OpenFeignConfig.class)
public interface ServiceFeign {

    /********************库存服务********************/
    /**
     * 添加库存
     * @param stockReqVO
     */
    @RequestMapping(value = "/service-stock/stock/addStock", method = RequestMethod.POST)
    void addStock(@RequestBody StockReqVO stockReqVO);

    /**
     * 减少库存
     * @param stockReqVO
     */
    @RequestMapping(value = "/service-stock/stock/reduceStock", method = RequestMethod.POST)
    void reduceStock(@RequestBody StockReqVO stockReqVO);

    /**
     * 判断商品是否存在
     * @param goodsId
     * @return
     */
    @RequestMapping(value = "/service-stock/stock/existGoods", method = RequestMethod.GET)
    StockResBO existGoods(@RequestParam String goodsId);


    /********************日志服务********************/
    /**
     * 保存日志
     *
     * @param sysLog 日志实体
     * @return succes、false
     */
    @PostMapping(value = "/service-log/saveLog", headers = {"Content-Type=application/json;charset=UTF-8"})
    Boolean saveLog(@RequestBody SysLog sysLog);

}
