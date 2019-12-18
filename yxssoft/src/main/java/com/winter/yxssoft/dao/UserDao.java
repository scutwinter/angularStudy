package com.winter.yxssoft.dao;

import com.winter.yxssoft.entity.User;

import java.util.List;

/**
 * Mybatis 测试
 */
public interface UserDao {
    /**
     * 返回数据列表
     * @return
     */
    List<User> findAllUsers();

    /**
     * 增加
     * @param user
     * @return
     */
    int insertUser(User user);

    /**
     * 更新
     * @param user
     * @return
     */
    int updateUser(User user);

    /**
     * 删除
     * @param id
     * @return
     */
    int delUser(Integer id);
}
