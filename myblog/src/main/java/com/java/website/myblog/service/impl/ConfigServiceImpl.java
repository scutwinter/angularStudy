package com.java.website.myblog.service.impl;

import com.java.website.myblog.dao.BlogConfigDao;
import com.java.website.myblog.entity.BlogConfig;
import com.java.website.myblog.service.ConfigService;
import org.springframework.stereotype.Service;
import org.thymeleaf.util.StringUtils;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class ConfigServiceImpl implements ConfigService {
    @Resource
    private BlogConfigDao blogConfigDao;

    public static final String websiteName = "Winter's Blog";
    public static final String websiteDescription = "坚持写博客是一种积累和进步";
    public static final String websiteLogo = "/admin/dist/img/logo2.png";
    public static final String websiteIcon = "/admin/dist/img/favicon.png";
    public static final String yourAvatar = "/admin/dist/img/winter.jpg";
    public static final String yourEmail = "scutwinter@vip.qq.com";
    public static final String yourName = "Winter";
    public static final String footerAbout = "Your personal blog. have fun.";
    public static final String footerICP = "粤ICP备 XXXXX-X号";
    public static final String footerCopyRight = "@2020 Winter";
    public static final String footerPoweredBy = "Winter's Blog";
    public static final String footerPoweredByURL = "##";

    @Override
    public int updateConfig(String configName, String configValue) {
        BlogConfig blogConfig=blogConfigDao.selectByPrimaryKey(configName);
        if(blogConfig!= null){
            blogConfig.setConfigValue(configValue);
            blogConfig.setUpdateTime(new Date());
            return blogConfigDao.updateByPrimaryKeySelective(blogConfig);
        }
        return 0;
    }

    @Override
    public Map<String, String> getAllConfigs() {
        List<BlogConfig> blogConfigs=blogConfigDao.selectAll();
        Map<String,String> configMap=blogConfigs.stream().collect(Collectors.toMap(BlogConfig::getConfigName,BlogConfig::getConfigValue));
        for (Map.Entry<String,String> config :configMap.entrySet()){
            if ("websiteName".equals(config.getKey()) && StringUtils.isEmpty(config.getValue())){
                config.setValue(websiteName);

            }
            if ("websiteDescription".equals(config.getKey()) && StringUtils.isEmpty(config.getValue())){
                config.setValue(websiteDescription);
            }
            if ("websiteLogo".equals(config.getKey()) && StringUtils.isEmpty(config.getValue())){
                config.setValue(websiteLogo);
            }
            if ("websiteIcon".equals(config.getKey()) && StringUtils.isEmpty(config.getValue())){
                config.setValue(websiteIcon);
            }
            if ("yourAvatar".equals(config.getKey()) && StringUtils.isEmpty(config.getValue())){
                config.setValue(yourAvatar);
            }
            if ("yourEmail".equals(config.getKey()) && StringUtils.isEmpty(config.getValue())){
                config.setValue(yourEmail);
            }
            if ("yourName".equals(config.getKey()) && StringUtils.isEmpty(config.getValue())){
                config.setValue(yourName);
            }
            if ("footerAbout".equals(config.getKey()) && StringUtils.isEmpty(config.getValue())){
                config.setValue(footerAbout);
            }
            if ("footerICP".equals(config.getKey()) && StringUtils.isEmpty(config.getValue())){
                config.setValue(footerICP);
            }
            if ("footerCopyRight".equals(config.getKey()) && StringUtils.isEmpty(config.getValue())){
                config.setValue(footerCopyRight);
            }
            if ("footerPoweredBy".equals(config.getKey()) && StringUtils.isEmpty(config.getValue())){
                config.setValue(footerPoweredBy);
            }
            if ("footerPoweredByURL".equals(config.getKey()) && StringUtils.isEmpty(config.getValue())){
                config.setValue(footerPoweredByURL);
            }
        }
        return configMap;
    }
}
