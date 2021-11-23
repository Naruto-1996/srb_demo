package com.atguigu.core.service;

import com.atguigu.core.pojo.entity.UserInfo;
import com.atguigu.core.pojo.query.UserInfoQuery;
import com.atguigu.core.pojo.vo.LoginVO;
import com.atguigu.core.pojo.vo.RegisterVO;
import com.atguigu.core.pojo.vo.UserInfoVO;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 用户基本信息 服务类
 * </p>
 *
 * @author liwenyang
 * @since 2021-11-02
 */
public interface UserInfoService extends IService<UserInfo> {

    // 用户注册
    void register(RegisterVO registerVO);

    // 用户登录
    UserInfoVO login(LoginVO loginVO, String ip);

    // 根据条件查询数据
    IPage<UserInfo> listPage(Page<UserInfo> pageParam, UserInfoQuery userInfoQuery);

    // 锁定或解锁用户
    void lock(Long id, Integer status);

    // 检验手机号是否注册
    boolean checkMobile(String mobile);
}
