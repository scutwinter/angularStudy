package com.java.website.myblog.service;

import com.java.website.myblog.entity.UserInfo;

public interface UserInfoService {
    UserInfo login(String username,String password);
    UserInfo getUserDetailById(Integer userId);
    int updatePassword(Integer userId,String originalPassword,String newPassword);
    boolean updateName(Integer userId,String userNickName);
}
