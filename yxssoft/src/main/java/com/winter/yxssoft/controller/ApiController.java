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

    /**
     * 新增一条数据
     * @param user
     * @return
     */
    @RequestMapping(value = "/users",method = RequestMethod.POST)
    @ResponseBody
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

    /**
     * 修改一条数据
     * @param tempUser
     * @return
     */
    @RequestMapping(value = "/users",method = RequestMethod.PUT)
    @ResponseBody
    public Result<Boolean> update(@RequestBody User tempUser){
        if(tempUser.getId()==null || tempUser.getId()<1 || StringUtils.isEmpty(tempUser.getName())){
            return ResultGenerator.genFailResult("缺少参数");
        }
        User user=usersMap.get(tempUser.getId());
        if(user== null){
            return ResultGenerator.genFailResult("参数异常");
        }
        user.setName(tempUser.getName());
        user.setPassword(tempUser.getPassword());
        usersMap.put(tempUser.getId(),tempUser);
        return ResultGenerator.genSuccessResult(true);
    }

    /**
     * 删除一条数据
     * @param id
     * @return
     */
    @RequestMapping(value = "/users/{id}",method = RequestMethod.DELETE)
    @ResponseBody
    public Result<Boolean> delete(@PathVariable("id") Integer id){
        if(id==null || id < 1 ){
            return ResultGenerator.genFailResult("缺少参数");
        }
        usersMap.remove(id);
        return ResultGenerator.genSuccessResult(true);
    }



}
