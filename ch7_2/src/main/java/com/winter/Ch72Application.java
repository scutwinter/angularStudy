package com.winter;

import java.util.ArrayList;
import java.util.List;

import org.apache.catalina.connector.Connector;
import org.apache.catalina.Context;
import org.apache.tomcat.util.descriptor.web.SecurityCollection;
import org.apache.tomcat.util.descriptor.web.SecurityConstraint;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.servlet.server.ServletWebServerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;



@Controller
@SpringBootApplication
public class Ch72Application {

    @RequestMapping("/")
    public String index(Model model){
        Person single=new Person("amy",8);

        List<Person> people=new ArrayList<Person>();
        Person p1 = new Person("winter",34);
        Person p2 = new Person("cyy",33);
        Person p3 = new Person("ady",1);

        people.add(p1);
        people.add(p2);
        people.add(p3);

        model.addAttribute("singlePerson",single);
        model.addAttribute("people",people);

        return "index";
    }

    public static void main(String[] args) {
        SpringApplication.run(Ch72Application.class, args);
    }

    /**
     * 这个是Spring 2.X的实现方式
     * @return
     */
    @Bean
    public ServletWebServerFactory serverFactory(){
        TomcatServletWebServerFactory tomcat=new TomcatServletWebServerFactory(){
            @Override
            protected void postProcessContext(Context context){ //表示对访问的上下文进行预处理
                SecurityConstraint securityConstraint=new SecurityConstraint();
                securityConstraint.setUserConstraint("confidential"); //机密的; 秘密的; 表示信任的;
                SecurityCollection securityCollection=new SecurityCollection();
                securityCollection.addPattern("/*");  //匹配根目录下的所有地址
                securityConstraint.addCollection(securityCollection);
                context.addConstraint(securityConstraint);

            }
        };
        tomcat.addAdditionalTomcatConnectors(createSslConnector());
        return tomcat;
    }

    /**
     * 下面这个是Spring 1.X的实现方式，区别在于EmbeddedServletContainerFactory换成了ServletWebServerFactory
     *  TomcatEmbeddedServletContainerFactory换成了TomcatServletWebServerFactory
     * @return
     */
    /*
    @Bean
    public EmbeddedServletContainerFactory servletContainer() {
        TomcatEmbeddedServletContainerFactory tomcat= new TomcatEmbeddedServletContainerFactory() {
            @Override
            protected void postProcessContext(Context context) {
                SecurityConstraint constraint = new SecurityConstraint();
                constraint.setUserConstraint("CONFIDENTIAL");
                SecurityCollection collection = new SecurityCollection();
                collection.addPattern("/*");
                constraint.addCollection(collection);
                context.addConstraint(constraint);
            }
        };
        tomcat.addAdditionalTomcatConnectors( connector ());
        return tomcat;
    }
     */

    private Connector createSslConnector() {
        Connector connector = new Connector("org.apache.coyote.http11.Http11NioProtocol");
        connector.setScheme("http");
        connector.setPort(8080);
        connector.setSecure(false);
        connector.setRedirectPort(8443);	//将对http:8080的访问重定向至https:8443，所以配置文件中的端口不能是8080，也要改为8443
        return connector;
    }

}
