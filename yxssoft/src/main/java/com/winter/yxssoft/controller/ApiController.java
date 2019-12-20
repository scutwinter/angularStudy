package com.winter.yxssoft.controller;

import com.winter.yxssoft.common.Result;
import com.winter.yxssoft.common.ResultGenerator;
import com.winter.yxssoft.entity.User;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@Controller
@RequestMapping("/api")
public class ApiController {
    static Map<Integer, User> usersMap = Collections.synchronizedMap(new HashMap<Integer, User>());

    static {
        User user = new User();
        user.setId(2);
        user.setName("user1");
        user.setPassword("123456");
        User user2 = new User();
        user2.setId(5);
        user2.setName("13-5");
        user2.setPassword("4");
        User user3 = new User();
        user3.setId(6);
        user3.setName("12");
        user3.setPassword("123");
        usersMap.put(2, user);
        usersMap.put(5, user2);
        usersMap.put(6, user3);
    }

    /**
     * 查询一条记录
     * @param id
     * @return
     */
    @RequestMapping(value = "/users/{id}",method = RequestMethod.GET)
    @ResponseBody
    public Result<User> getOne(@PathVariable("id") Integer id){
        if(id == null || id<1 ){
            return ResultGenerator.genFailResult("缺少参数");
        }
        User user=usersMap.get(id);
        if (user == null)
            return ResultGenerator.genFailResult("无此数据");
        return ResultGenerator.genSuccessResult(user);
    }

    /**
     * 查询所有记录
     * @return
     */
    @RequestMapping(value = "/users",method = RequestMethod.GET)
    @ResponseBody
    public Result<List<User>> getAll(){
        List<User> users=new ArrayList<User>(usersMap.values());
        return ResultGenerator.genSuccessResult(users);
    }

    public Result<Boolean> insert(@RequestBody User user){
        if(StringUtils.isEmpty(user.getId()) || StringUtils.isEmpty(user.getName()) || StringUtils.isEmpty(user.getPassword())){
            return ResultGenerator.genFailResult("缺少参数");
        }
        if(usersMap.containsKey(user.getId())){
            return ResultGenerator.genFailResult("重复的id值");
        }
        usersMap.put(user.getId(),user);
        return ResultGenerator.genSuccessResult(true);
    }

}
