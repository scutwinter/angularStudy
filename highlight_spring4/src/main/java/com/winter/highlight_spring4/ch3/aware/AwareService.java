package com.winter.highlight_spring4.ch3.aware;
/*
Spring Aware的目的是为了让Bean获得Spring容器的服务：
BeanNameAware 获得容器中Bean的名称
BeanFactoryAware 获得当前Bean factory，这样可以调用容器的服务
ApplicationContextAware* 当前Application context,这样可以调用容器的服务
MessageSourceAware 获得Message source，这样可以获得文本信息
ApplicationEventPublisherAware 应用事件发布器，可以发布事件，类似2.5节内容
ResourceLoaderAware  获得资源加载器，可以获得外部资源文件
 */
import java.io.IOException;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.context.ResourceLoaderAware;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;

@Service
//1 同时实现BeanNameAware和ResourceLoaderAware接口，获得Bean名称和资源加载的服务
public class AwareService implements BeanNameAware,ResourceLoaderAware {
    private String beanName;
    private ResourceLoader loader;

    @Override
    //2 实现ResourceLoaderAware需要重写setResourceLoader
    public void setResourceLoader(ResourceLoader resourceLoader){
        this.loader=resourceLoader;
    }

    @Override
    //3 实现BeanNameAware需要重写setBeanName
    public void setBeanName(String name){
        this.beanName=name;
    }

    public void outputResult(){
        System.out.println("Bean 的名称为：" + beanName);
        Resource resource=loader.getResource("classpath:com/winter/highlight_spring4/ch3/aware/winter.txt");
        try{
            System.out.println("ResourceLoader 加载的文件内容为："+IOUtils.toString(resource.getInputStream()));
        }catch (IOException ex){
            ex.printStackTrace();
        }
    }
}
