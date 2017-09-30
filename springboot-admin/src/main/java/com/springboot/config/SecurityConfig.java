package com.springboot.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * Created by zhenghuasheng on 2017/9/30.
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)//允许进入页面方法前检验
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable() //HTTP with Disable CSRF
                .authorizeRequests() //Authorize Request Configuration
                .antMatchers( "/login",
                        "/api/**",
                        "/**/heapdump", "/**/*.css", "/img/**", "/third-party/**",
                        "/**/loggers",
                        "/**/liquibase",
                        "/**/logfile",
                        "/**/flyway",
                        "/**/auditevents",
                        "/**/jolokia").permitAll() //放开"/api/**"：为了给被监控端免登录注册并解决Log与Logger冲突
                .and()
                .authorizeRequests()
                .antMatchers("/**").hasRole("USER")
                .antMatchers("/**").authenticated()
                .and() //Login Form configuration for all others
                .formLogin()
                .loginPage("/login.html")
                .loginProcessingUrl("/login").permitAll()
                .defaultSuccessUrl("/")
                .and() //Logout Form configuration
                .logout().logoutUrl("/logout")
                .deleteCookies("remove")
                .logoutSuccessUrl("/login.html").permitAll()
                .and().rememberMe().tokenValiditySeconds(60 * 60 * 24 * 7)
                .and()
                .httpBasic();
    }
}
