package com.atguigu.core.service.impl;

import com.atguigu.base.utils.JwtUtils;
import com.atguigu.common.exception.Assert;
import com.atguigu.common.result.ResultEnum;
import com.atguigu.common.utils.MD5;
import com.atguigu.core.mapper.UserAccountMapper;
import com.atguigu.core.mapper.UserInfoMapper;
import com.atguigu.core.mapper.UserLoginRecordMapper;
import com.atguigu.core.pojo.entity.UserAccount;
import com.atguigu.core.pojo.entity.UserLoginRecord;
import com.atguigu.core.pojo.query.UserInfoQuery;
import com.atguigu.core.pojo.vo.LoginVO;
import com.atguigu.core.pojo.vo.RegisterVO;
import com.atguigu.core.pojo.vo.UserInfoVO;
import com.atguigu.core.service.UserInfoService;
import com.atguigu.core.pojo.entity.UserInfo;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.val;
import org.apache.commons.codec.digest.Md5Crypt;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;

/**
 * <p>
 * 用户基本信息 服务实现类
 * </p>
 *
 * @author liwenyang
 * @since 2021-11-02
 */
@Service
public class UserInfoServiceImpl extends ServiceImpl<UserInfoMapper, UserInfo> implements UserInfoService {

    @Resource
    private UserAccountMapper userAccountMapper;

    @Resource
    private UserLoginRecordMapper userLoginRecordMapper;

    // 加入事务 如果报错就回滚
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void register(RegisterVO registerVO) {

        // 判断手机号是否被注册过了
        QueryWrapper<UserInfo> userInfoQueryWrapper = new QueryWrapper<>();
        userInfoQueryWrapper.eq("mobile", registerVO.getMobile());
        Integer count = baseMapper.selectCount(userInfoQueryWrapper);
        // 断言手机号在数据库中不存在 否则报手机号已存在的信息
        Assert.isTrue(count == 0, ResultEnum.MOBILE_EXIST_ERROR);

        // 插入用户信息, user_info
        UserInfo userInfo = new UserInfo();
        userInfo.setUserType(registerVO.getUserType());
        userInfo.setNickName(registerVO.getMobile());
        userInfo.setName(registerVO.getMobile());
        userInfo.setMobile(registerVO.getMobile());
        userInfo.setPassword(MD5.encrypt(registerVO.getPassword()));
        userInfo.setStatus(UserInfo.STATUS_NORMAL);
        userInfo.setHeadImg(UserInfo.USER_AVATAR);
        baseMapper.insert(userInfo);

        // 插入用户账户记录, user_account
        UserAccount userAccount = new UserAccount();
        userAccount.setUserId(userInfo.getId());
        userAccountMapper.insert(userAccount);
    }

    // 加入事务
    @Transactional(rollbackFor = Exception.class)
    @Override
    public UserInfoVO login(LoginVO loginVO, String ip) {
        String mobile = loginVO.getMobile();
        String password = loginVO.getPassword();
        Integer userType = loginVO.getUserType();

        // 用户是否存在
        QueryWrapper<UserInfo> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("mobile", mobile)
                .eq("user_type", userType);
        UserInfo userInfo = baseMapper.selectOne(queryWrapper);

        Assert.notNull(userInfo, ResultEnum.LOGIN_MOBILE_ERROR);

        // 密码是否正确
        Assert.equals(MD5.encrypt(password), userInfo.getPassword(), ResultEnum.LOGIN_PASSWORD_ERROR);
        // 用户是否被禁用
        Assert.equals(userInfo.getStatus(), UserInfo.STATUS_NORMAL, ResultEnum.LOGIN_LOKED_ERROR);
        // 记录登录日志
        UserLoginRecord userLoginRecord = new UserLoginRecord();
        userLoginRecord.setUserId(userInfo.getId());
        userLoginRecord.setIp(ip);
        userLoginRecordMapper.insert(userLoginRecord);
        // 生成token
        String token = JwtUtils.createToken(userInfo.getId(), userInfo.getName());
        // 组装userInfoVo对象返回给前端展示
        UserInfoVO userInfoVO = new UserInfoVO();
        userInfoVO.setToken(token);
        userInfoVO.setName(userInfo.getName());
        userInfoVO.setNickName(userInfo.getNickName());
        userInfoVO.setHeadImg(userInfo.getHeadImg());
        userInfoVO.setUserType(userInfo.getUserType());
        userInfoVO.setMobile(mobile);
        // 返回
        return userInfoVO;
    }

    @Override
    public IPage<UserInfo> listPage(Page<UserInfo> pageParam, UserInfoQuery userInfoQuery) {
        if (userInfoQuery == null) {
            return baseMapper.selectPage(pageParam, null);
        }

        String mobile = userInfoQuery.getMobile();
        Integer status = userInfoQuery.getStatus();
        Integer userType = userInfoQuery.getUserType();

        QueryWrapper<UserInfo> userInfoQueryWrapper = new QueryWrapper<>();

        // 这里加入一个boolean判断，如果有值就组装进查询，没值就不组装
        userInfoQueryWrapper
                .eq(StringUtils.hasLength(mobile), "mobile", mobile)
                .eq(status != null, "status", status)
                .eq(userType != null, "user_type", userType);
        return baseMapper.selectPage(pageParam, userInfoQueryWrapper);
    }

    @Override
    public void lock(Long id, Integer status) {
        UserInfo userInfo = new UserInfo();
        userInfo.setId(id);
        userInfo.setStatus(status);

        baseMapper.updateById(userInfo);
    }

    @Override
    public boolean checkMobile(String mobile) {
        QueryWrapper<UserInfo> infoQueryWrapper = new QueryWrapper<>();
        infoQueryWrapper.eq("mobile", mobile);
        Integer count = baseMapper.selectCount(infoQueryWrapper);
        return count > 0;
    }
}
