package com.java.website.myblog.service.impl;

import com.java.website.myblog.dao.UserInfoDao;
import com.java.website.myblog.entity.UserInfo;
import com.java.website.myblog.service.UserInfoService;
import com.java.website.myblog.util.MD5Util;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class UserInfoServiceImpl implements UserInfoService {
    @Resource
    private UserInfoDao userInfoDao;
    @Override
    public UserInfo login(String username, String password) {
        String passwordMD5 = MD5Util.MD5Encode(password,"UTF-8");
        return userInfoDao.login(username,passwordMD5);
    }

    @Override
    public UserInfo getUserDetailById(Integer userId) {
        return userInfoDao.getUserDetailById(userId);
    }

    @Override
    public boolean updatePassword(Integer userId, String originalPassword, String newPassword) {
        String originalPasswordMD5 = MD5Util.MD5Encode(originalPassword,"UTF-8");
        String newPasswordMD5 = MD5Util.MD5Encode(newPassword,"UTF-8");
        return userInfoDao.updatePassword(userId,originalPasswordMD5,newPasswordMD5);
    }
}
