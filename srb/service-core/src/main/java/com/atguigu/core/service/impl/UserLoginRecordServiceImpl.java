package com.atguigu.core.service.impl;

import com.atguigu.core.mapper.UserLoginRecordMapper;
import com.atguigu.core.service.UserLoginRecordService;
import com.atguigu.core.pojo.entity.UserLoginRecord;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 用户登录记录表 服务实现类
 * </p>
 *
 * @author liwenyang
 * @since 2021-11-02
 */
@Service
public class UserLoginRecordServiceImpl extends ServiceImpl<UserLoginRecordMapper, UserLoginRecord> implements UserLoginRecordService {

    @Override
    public List<UserLoginRecord> getListTop50(Long userId) {

        QueryWrapper<UserLoginRecord> loginRecordQueryWrapper = new QueryWrapper<>();
        loginRecordQueryWrapper
                .eq("user_id", userId)
                .orderByDesc("id")
                .last("limit 50");
        return baseMapper.selectList(loginRecordQueryWrapper);

    }
}
