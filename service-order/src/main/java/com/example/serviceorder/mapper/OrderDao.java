package com.example.serviceorder.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.serviceorder.mapper.entity.OrderDO;
import org.springframework.stereotype.Repository;

/**
 * @description:
 * @author: zhenglifei
 * @create: 2022/4/19 10:36
 **/
@Repository
public interface OrderDao extends BaseMapper<OrderDO> {
}
