package com.java.website.myblog.controller.admin;

import com.java.website.myblog.controller.common.Result;
import com.java.website.myblog.controller.common.ResultGenerator;
import com.java.website.myblog.service.TagService;
import com.java.website.myblog.util.PageUtil;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@Controller
@RequestMapping("/admin")
public class TagController {
    @Resource
    private TagService tagService;

    @GetMapping("/tags")
    public  String categoryPage(HttpServletRequest request){
        request.setAttribute("path","tags");
        return "admin/tag";
    }

    @RequestMapping(value = "/tags/list",method = RequestMethod.GET)
    @ResponseBody
    public Result list(@RequestParam Map<String,Object> param){
        if(StringUtils.isEmpty(param.get("page")) || StringUtils.isEmpty(param.get("limit"))){
            return ResultGenerator.genFailResult("参数异常！");
        }
        PageUtil pageUtil= new PageUtil(param);
        return ResultGenerator.genSuccessResult(tagService.getBlogTagPage(pageUtil));
    }

    @PostMapping("/tags/save")
    @ResponseBody
    public Result save(@RequestParam("tagName") String tagName){
        if(StringUtils.isEmpty(tagName)){
            return ResultGenerator.genFailResult("参数异常！");
        }
        if(tagService.saveTag(tagName)){
            return ResultGenerator.genSuccessResult();
        }else {
            return ResultGenerator.genFailResult("标签名称重复");
        }
    }

    @PostMapping("/tags/delete")
    @ResponseBody
    public Result delete(@RequestBody Integer[] ids){
        if(ids.length<1){
            return ResultGenerator.genFailResult("参数异常！");
        }
        if(tagService.deleteBatch(ids)){
            return ResultGenerator.genSuccessResult();
        }else{
            return ResultGenerator.genFailResult("有关联数据，请勿强行删除");
        }

    }

}
