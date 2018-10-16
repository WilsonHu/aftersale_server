package com.eservice.api.config;

import com.eservice.api.service.impl.UserServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Bean
    UserDetailsService customUserService() {
//        return new UserService();
        return new UserServiceImpl();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(customUserService());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        UsernamePasswordAuthenticationFilter filter = new UsernamePasswordAuthenticationFilter();
        filter.setUsernameParameter("account");
        filter.setPasswordParameter("password");
        http.csrf().disable().authorizeRequests()
                .antMatchers("/").permitAll()
                .antMatchers(HttpMethod.POST, "/login").permitAll()
                /**
                 * 运行所有用户访问，不需要带JWT
                 */
                .antMatchers(HttpMethod.POST, "/wechat/user/info/loginGetUnionIdAndSave").permitAll()
                .antMatchers(HttpMethod.GET, "/wechat/user/info/wechatMessageVerify").permitAll()
                .antMatchers(HttpMethod.GET, "/wechat/user/info/wechatRedirect").permitAll()

                .anyRequest().authenticated()
                .and()
                // We filter the api/login requests
                .addFilterBefore(new JWTLoginFilter("/login", authenticationManager()),
                        UsernamePasswordAuthenticationFilter.class)
                // And filter other requests to check the presence of JWT in header
                ///添加自定义的过滤器: JWT过滤器
                .addFilterBefore(new JWTAuthenticationFilter(),
                        UsernamePasswordAuthenticationFilter.class);
    }

}
