package com.java.website.myblog.controller.admin;

import com.java.website.myblog.controller.common.Result;
import com.java.website.myblog.controller.common.ResultGenerator;
import com.java.website.myblog.entity.BlogCategory;
import com.java.website.myblog.service.BlogCategoryService;
import com.java.website.myblog.util.PageUtil;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@Controller
@RequestMapping("/admin")
public class BlogCategoryController {
    @Resource
    private BlogCategoryService blogCategoryService;

    @GetMapping("/categories")
    public  String categoryPage(HttpServletRequest request){
        request.setAttribute("path","categories");
        return "admin/category";
    }

    /**
     * 分类列表
     * @param params
     * @return
     */
    @RequestMapping(value = "/categories/list",method = RequestMethod.GET)
    @ResponseBody
    public Result list(@RequestParam Map<String,Object> params){
        if (StringUtils.isEmpty(params.get("page")) || StringUtils.isEmpty(params.get("limit"))){
            return ResultGenerator.genFailResult("参数异常");
        }
        PageUtil pageUtil = new PageUtil(params);
        return ResultGenerator.genSuccessResult(blogCategoryService.getBlogCategoryPage(pageUtil));
    }

    /**
     * 分类保存
     * @param categoryName
     * @param categoryIcon
     * @return
     */
    @RequestMapping(value = "/categories/save",method = RequestMethod.POST)
    @ResponseBody
    public Result save(@RequestParam("categoryName") String categoryName,
                       @RequestParam("categoryIcon") String categoryIcon){
        if (StringUtils.isEmpty(categoryName)){
            return ResultGenerator.genFailResult("请输入分类名称!");
        }
        if (StringUtils.isEmpty(categoryIcon)){
            return ResultGenerator.genFailResult("请选择分类图标!");
        }
        if (blogCategoryService.saveCategory(categoryName, categoryIcon)){
            return ResultGenerator.genSuccessResult();
        }else {
            return ResultGenerator.genFailResult("分类名称重复");
        }
    }

    /**
     * 分类修改
     * @param categoryId
     * @param categoryName
     * @param categoryIcon
     * @return
     */
    @RequestMapping(value="/categories/update",method = RequestMethod.POST)
    @ResponseBody
    public Result update(@RequestParam("categoryId") Integer categoryId,
                         @RequestParam("categoryName") String categoryName,
                         @RequestParam("categoryIcon") String categoryIcon){
        if(StringUtils.isEmpty(categoryName)){
            return ResultGenerator.genFailResult("请输入分类名称!");
        }
        if(categoryId ==null || categoryId<1){
            return ResultGenerator.genFailResult("非法参数!");
        }
        if(StringUtils.isEmpty(categoryIcon)){
            return ResultGenerator.genFailResult("请选择分类图标!");
        }
        if(blogCategoryService.updateCategory(categoryId,categoryName,categoryIcon)){
            return ResultGenerator.genSuccessResult();
        }else{
            return ResultGenerator.genFailResult("分类名称重复");
        }
    }

    /**
     * 分类删除
     * @param ids
     * @return
     */
    @RequestMapping(value = "/categories/delete",method = RequestMethod.POST)
    @ResponseBody
    public Result delete(@RequestBody Integer[] ids){
        if(ids.length<1){
            return ResultGenerator.genFailResult("参数异常!");
        }
        if (blogCategoryService.deleteBatch(ids)){
            return ResultGenerator.genSuccessResult();
        }else{
            return ResultGenerator.genFailResult("删除失败");
        }
    }

    /**
     * 详情
     * @param id
     * @return
     */
    @GetMapping("/categories/info/{id}")
    @ResponseBody
    public Result info(@PathVariable("id") Integer id){
        if (id ==null || id<1){
            return ResultGenerator.genFailResult("非法参数!");
        }
        BlogCategory category = blogCategoryService.selectById(id);
        return ResultGenerator.genSuccessResult(category);
    }
}
