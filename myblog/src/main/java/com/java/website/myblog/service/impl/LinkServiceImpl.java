package com.java.website.myblog.service.impl;

import com.java.website.myblog.dao.LinkDao;
import com.java.website.myblog.entity.Link;
import com.java.website.myblog.service.LinkService;
import com.java.website.myblog.util.PageResult;
import com.java.website.myblog.util.PageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LinkServiceImpl implements LinkService {

    @Autowired
    private LinkDao linkDao;

    @Override
    public PageResult getLinkPage(PageUtil pageUtil) {
        List<Link> links = linkDao.findLinkList(pageUtil);
        int total = linkDao.getTotalLinks();
        PageResult pageResult = new PageResult(links,total,pageUtil.getLimit(),pageUtil.getPage());
        return pageResult;
    }

    @Override
    public Boolean saveLink(Link link) {
        return linkDao.insertSelective(link)>0;
    }

    @Override
    public Boolean updateLink(Link tempLink) {
        return linkDao.updateByPrimaryKeySelective(tempLink)>0;
    }

    @Override
    public Link selectById(Integer id) {
        return linkDao.selectByPrimaryKey(id);
    }

    @Override
    public Boolean deleteBatch(Integer[] ids) {
        return linkDao.deleteBatch(ids)>0;
    }
}
