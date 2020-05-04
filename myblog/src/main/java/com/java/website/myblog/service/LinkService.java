package com.java.website.myblog.service;

import com.java.website.myblog.entity.BlogCategory;
import com.java.website.myblog.entity.Link;
import com.java.website.myblog.util.PageResult;
import com.java.website.myblog.util.PageUtil;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;


@Component
public interface LinkService {
    PageResult getLinkPage(PageUtil pageUtil);

    Boolean saveLink(Link link);

    Boolean deleteBatch(Integer[] ids);

    Link selectById(Integer id);

    Boolean updateLink(Link tempLink);

    int getTotalLinks();

    Map<Byte, List<Link>> getLinksForLinkPage();

}
