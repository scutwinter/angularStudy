package com.winter.yxssoft.dao;

import com.winter.yxssoft.entity.AdminUser;

import java.util.List;
import java.util.Map;

public interface AdminUserDao {
    int deleteByPrimaryKey(Integer id);

    int insert(AdminUser record);

    int insertSelective(AdminUser record);

    AdminUser selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(AdminUser record);

    int updateByPrimaryKey(AdminUser record);

    int getTotalAdminUser(Map param);

    List<AdminUser> findAdminUsers(Map param);

}