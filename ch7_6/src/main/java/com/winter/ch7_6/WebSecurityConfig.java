package com.winter.ch7_6;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception{
        http
                .authorizeRequests()
                //1 设置Spring Security 对/和/"login"路径不拦截
                .antMatchers("/","/login").permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin()
                //2 设置Spring Security的登陆页面访问的路径为/lgin
                .loginPage("/login")
                //3 登陆成功后转向/chat路径
                .defaultSuccessUrl("/chat")
                .permitAll()
                .and()
                .logout()
                .permitAll();
    }

    /**
     * 4 在内存中分别配置两个用户，密码和用户名一致，角色是USER
     *   在Spring Security 5.0之前，PasswordEncoder 的默认值为 NoOpPasswordEncoder 既表示为纯文本密码，
     *   在实际的开发过程中 PasswordEncoder 大多数都会设值为 BCryptPasswordEncoder ，但是这样会导致几个问题：
     *   1、在应用程序中使用 BCryptPasswordEncoder 编码方式编码后的密码，很难轻松的迁移；
     *   2、密码存储后，会再次被更改；
     *   3、作为一个应用中的安全框架，Spring Security 不能频繁地进行中断更改；
     *
     * 在 Spring Security 5.0.x 以后，密码的一般格式为：{ID} encodedPassword ，ID 主要用于查找 PasswordEncoder
     * 对应的编码标识符，并且encodedPassword 是所选的原始编码密码 PasswordEncoder。ID 必须书写在密码的前面，
     * 开始用{，和 结束 }。如果 ID 找不到，ID 则为null。例如，在相关的源码中，我找到了 Spring Security
     * 定义的不同的编码方式的列表 ID。所有原始密码都是“ password ”。
     * @param auth
     * @throws Exception
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception
    {
        auth
                .inMemoryAuthentication()
                .withUser("wyf").password("wyf").roles("USER")
                .and()
                .withUser("winter").password("winter").roles("USER")
                .and()
                .passwordEncoder(new CustomPasswordEncoder());
    }

    /**
     * 5 /resource/static/目录下的静态资源，Spring Security不拦截
     * @param web
     * @throws Exception
     */
    @Override
    public void configure(WebSecurity web) throws Exception{
        web.ignoring().antMatchers("/resources/static/**");
    }
}
