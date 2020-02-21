package com.java.website.myblog.controller.admin;

import com.java.website.myblog.controller.common.Result;
import com.java.website.myblog.controller.common.ResultGenerator;
import com.java.website.myblog.entity.BlogCategory;
import com.java.website.myblog.entity.Link;
import com.java.website.myblog.service.LinkService;
import com.java.website.myblog.util.PageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@Controller
@RequestMapping("/admin")
public class LinkController {

    @Autowired
    private LinkService linkService;

    @GetMapping("/links")
    public String linkPage(HttpServletRequest request){
        request.setAttribute("path","links");
        return "admin/link";
    }

    @GetMapping("/links/list")
    @ResponseBody
    public Result list(@RequestParam Map<String,Object> params){
        if (StringUtils.isEmpty(params.get("page")) || StringUtils.isEmpty(params.get("limit"))){
            return ResultGenerator.genFailResult("参数异常");
        }
        PageUtil pageUtil = new PageUtil(params);
        return ResultGenerator.genSuccessResult(linkService.getLinkPage(pageUtil));
    }

    @GetMapping("/links/info/{id}")
    @ResponseBody
    public Result info(@PathVariable("id") Integer id){
        if (id ==null || id<1){
            return ResultGenerator.genFailResult("非法参数!");
        }
        Link link = linkService.selectById(id);
        return ResultGenerator.genSuccessResult(link);
    }
    /**
     * 友情链接保存
     * @param linkType
     * @param linkName
     * @param linkUrl
     * @param linkRank
     * @param linkDescription
     * @return
     */
    @RequestMapping(value = "/links/save",method = RequestMethod.POST)
    @ResponseBody
    public Result save(@RequestParam("linkType") Integer linkType,
                       @RequestParam("linkName") String linkName,
                       @RequestParam("linkUrl") String linkUrl,
                       @RequestParam("linkRank") Integer linkRank,
                       @RequestParam("linkDescription") String linkDescription){
        if (linkType==null){
            return ResultGenerator.genFailResult("请选择友情链接类别!");
        }
        if (linkRank == null){
            return ResultGenerator.genFailResult("友情链接排序规则出错!");
        }
        if (StringUtils.isEmpty(linkName)){
            return ResultGenerator.genFailResult("请录入友情链接名称!");
        }
        if (StringUtils.isEmpty(linkUrl)){
            return ResultGenerator.genFailResult("请录入友情链接地址!");
        }
        if (StringUtils.isEmpty(linkDescription)){
            return ResultGenerator.genFailResult("请录入友情链接描述!");
        }
        Link link=new Link();
        link.setLinkType(linkType.byteValue());
        link.setLinkName(linkName);
        link.setLinkUrl(linkUrl);
        link.setLinkRank(linkRank);
        link.setLinkDescription(linkDescription);
        return ResultGenerator.genSuccessResult(linkService.saveLink(link));
    }

    @RequestMapping(value = "/links/update", method = RequestMethod.POST)
    @ResponseBody
    public Result update(@RequestParam("linkId") Integer linkId,
                         @RequestParam("linkType") Integer linkType,
                         @RequestParam("linkName") String linkName,
                         @RequestParam("linkUrl") String linkUrl,
                         @RequestParam("linkRank") Integer linkRank,
                         @RequestParam("linkDescription") String linkDescription) {
        Link tempLink = linkService.selectById(linkId);
        if (tempLink == null) {
            return ResultGenerator.genFailResult("无数据！");
        }
        if (linkType<0){
            return ResultGenerator.genFailResult("请选择友情链接类别!");
        }
        if (linkRank == null){
            return ResultGenerator.genFailResult("友情链接排序规则出错!");
        }
        if (StringUtils.isEmpty(linkName)){
            return ResultGenerator.genFailResult("请录入友情链接名称!");
        }
        if (StringUtils.isEmpty(linkUrl)){
            return ResultGenerator.genFailResult("请录入友情链接地址!");
        }
        if (StringUtils.isEmpty(linkDescription)){
            return ResultGenerator.genFailResult("请录入友情链接描述!");
        }
        tempLink.setLinkType(linkType.byteValue());
        tempLink.setLinkRank(linkRank);
        tempLink.setLinkName(linkName);
        tempLink.setLinkUrl(linkUrl);
        tempLink.setLinkDescription(linkDescription);
        return ResultGenerator.genSuccessResult(linkService.updateLink(tempLink));
    }

    @RequestMapping(value = "/links/delete",method = RequestMethod.POST)
    @ResponseBody
    public Result delete(@RequestBody Integer[] ids){
        if(ids.length<1){
            return ResultGenerator.genFailResult("参数异常!");
        }
        if (linkService.deleteBatch(ids)){
            return ResultGenerator.genSuccessResult();
        }else{
            return ResultGenerator.genFailResult("删除失败");
        }
    }


}
