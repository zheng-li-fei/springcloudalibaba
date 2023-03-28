package com.zlf.serverauth.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zlf.serverauth.dao.entity.AuthDO;
import org.springframework.stereotype.Repository;

/**
 * @description:
 * @author: zhenglifei
 * @create: 2022/4/19 10:36
 **/
@Repository
public interface AuthDao extends BaseMapper<AuthDO> {
}
