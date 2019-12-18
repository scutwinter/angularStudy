package com.winter.yxssoft.controller;

import com.winter.yxssoft.dao.UserDao;
import com.winter.yxssoft.entity.User;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
public class MyBatisController {

    @Resource
    UserDao userDao;

    @GetMapping("/users/mybatis/queryAll")
    public List<User> queryAll(){
        return userDao.findAllUsers();
    }

    @GetMapping("/users/mybatis/insert")
    public Boolean insert(String name,String pwd){
        if (StringUtils.isEmpty(name) || StringUtils.isEmpty(pwd)){
            return false;
        }
        User user=new User();
        user.setName(name);
        user.setPassword(pwd);
        return userDao.insertUser(user)>0;
    }

    // 修改一条记录
    @GetMapping("/users/mybatis/update")
    public Boolean update(Integer id, String name, String password) {
        if (id == null || id < 1 || StringUtils.isEmpty(name) || StringUtils.isEmpty(password)) {
            return false;
        }
        User user = new User();
        user.setId(id);
        user.setName(name);
        user.setPassword(password);
        return userDao.updateUser(user) > 0;
    }

    // 删除一条记录
    @GetMapping("/users/mybatis/delete")
    public Boolean delete(Integer id) {
        if (id == null || id < 1) {
            return false;
        }
        return userDao.delUser(id) > 0;
    }
}
