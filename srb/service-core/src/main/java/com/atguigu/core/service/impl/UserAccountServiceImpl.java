package com.atguigu.core.service.impl;

import com.atguigu.core.mapper.UserAccountMapper;
import com.atguigu.core.service.UserAccountService;
import com.atguigu.core.pojo.entity.UserAccount;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户账户 服务实现类
 * </p>
 *
 * @author liwenyang
 * @since 2021-11-02
 */
@Service
public class UserAccountServiceImpl extends ServiceImpl<UserAccountMapper, UserAccount> implements UserAccountService {

}
