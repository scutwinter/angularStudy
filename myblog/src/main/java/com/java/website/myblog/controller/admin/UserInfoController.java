package com.java.website.myblog.controller.admin;

import com.java.website.myblog.entity.UserInfo;
import com.java.website.myblog.service.UserInfoService;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/admin")
public class UserInfoController {
    @Resource
    private UserInfoService userInfoService;

    @RequestMapping(value = "/login",method =RequestMethod.GET)
    public String login(){
        return "admin/login";
    }

    @RequestMapping(value = "/login",method =RequestMethod.POST)
    public String login(@RequestParam("userName") String userName,
                        @RequestParam("password") String password,
                        @RequestParam("verifyCode") String verifyCode,
                        HttpSession session){
        if (StringUtils.isEmpty(verifyCode)){
            session.setAttribute("errorMsg","验证码不能为空");
            return "admin/login";
        }
        if (StringUtils.isEmpty(userName) || StringUtils.isEmpty(password)){
            session.setAttribute("errorMsg","用户名和密码不能为空");
            return "admin/login";
        }
        String kaptchaCode = session.getAttribute("verifyCode") + "";
        if (StringUtils.isEmpty(kaptchaCode) || !verifyCode.equals(kaptchaCode)){
            session.setAttribute("errorMsg","验证码错误");
            return "admin/login";
        }
        UserInfo userInfo=userInfoService.login(userName,password);
        if(userInfo!=null){
            session.setAttribute("loginUser",userInfo.getUserName());
            session.setAttribute("loginUserId",userInfo.getUserId());
            session.setMaxInactiveInterval(60 * 60 * 2);
            return "redirect:/admin/profile";
        }else{
            session.setAttribute("errorMsg","登陆失败");
            return "admin/login";
        }

    }

    @GetMapping("/profile")
    public String profile(HttpServletRequest request){
        Integer loginUserId=(int) request.getSession().getAttribute("loginUserId");
        UserInfo userInfo=userInfoService.getUserDetailById(loginUserId);
        if (userInfo == null){
            return "admin/login";
        }
        request.setAttribute("path","profile");
        request.setAttribute("loginUserName",userInfo.getUserName());
        request.setAttribute("nickName",userInfo.getUserNickname());
        return "admin/index";
    }

    @PostMapping("/profile/password")
    @ResponseBody
    public String passwordUpdate(HttpServletRequest request,
                                 @RequestParam("originalPassword") String originalPassword,
                                 @RequestParam("newPassword") String newPassword){
        if(StringUtils.isEmpty(originalPassword) || StringUtils.isEmpty(newPassword)){
            return "参数不能为空";
        }
        Integer loginUserId=(int) request.getSession().getAttribute("loginUserId");
        if (userInfoService.updatePassword(loginUserId,originalPassword,newPassword)){
            //修改成功后清空session中的数据，前端控制跳转至登录页
            request.getSession().removeAttribute("loginUserId");
            request.getSession().removeAttribute("loginUser");
            request.getSession().removeAttribute("errorMsg");
            return "success";
        }else{
            return "修改失败";
        }
    }

}