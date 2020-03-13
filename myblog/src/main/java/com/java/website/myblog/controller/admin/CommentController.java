package com.java.website.myblog.controller.admin;

import com.java.website.myblog.controller.common.Result;
import com.java.website.myblog.controller.common.ResultGenerator;
import com.java.website.myblog.service.CommentService;
import com.java.website.myblog.util.PageUtil;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@Controller
@RequestMapping("/admin")
public class CommentController {
    @Resource
    private CommentService commentService;

    @GetMapping("/comments")
    public String list(HttpServletRequest request){
        request.setAttribute("path","comments");
        return "admin/comments";
    }

    /**
     * 评论列表
     * @param params
     * @return
     */
    @GetMapping("/comments/list")
    @ResponseBody
    public Result list(@RequestParam Map<String,Object> params){
        if (StringUtils.isEmpty(params.get("page")) || StringUtils.isEmpty(params.get("limit"))){
            return ResultGenerator.genFailResult("参数异常");
        }
        PageUtil pageUtil= new PageUtil(params);
        return ResultGenerator.genSuccessResult(commentService.getCommentsPage(pageUtil));
    }
}
