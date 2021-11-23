package com.atguigu.core.service;

import com.atguigu.core.pojo.entity.UserLoginRecord;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 用户登录记录表 服务类
 * </p>
 *
 * @author liwenyang
 * @since 2021-11-02
 */
public interface UserLoginRecordService extends IService<UserLoginRecord> {

    // 获取用户的登录日志
    List<UserLoginRecord> getListTop50(Long userId);
}
