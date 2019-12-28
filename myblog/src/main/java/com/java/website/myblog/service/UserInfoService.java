package com.java.website.myblog.service;

import com.java.website.myblog.entity.UserInfo;

public interface UserInfoService {
    UserInfo login(String username,String password);
    UserInfo getUserDetailById(Integer userId);
    boolean updatePassword(Integer userId,String originalPassword,String newPassword);
}
