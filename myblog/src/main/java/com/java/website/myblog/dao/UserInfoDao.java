package com.java.website.myblog.dao;

import com.java.website.myblog.entity.UserInfo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

@Component
public interface UserInfoDao {
    int deleteByPrimaryKey(Integer id);

    int insert(UserInfo record);

    int insertSelective(UserInfo record);

    UserInfo selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(UserInfo record);

    int updateByPrimaryKey(UserInfo record);

    /**
     * login方法，因为有两个参数，因此使用@Param注解
     * @param userName
     * @param password
     * @return
     */
    UserInfo login(@Param("userName") String userName,@Param("password") String password);

    UserInfo getUserDetailById(Integer userId);

    boolean updatePassword(@Param("userId") Integer userId,
                           @Param("originalPassword") String originalPassword,
                           @Param("newPassword") String newPassword);
}