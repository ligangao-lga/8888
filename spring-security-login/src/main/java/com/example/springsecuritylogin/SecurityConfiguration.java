package com.example.springsecuritylogin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;


@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
	
	@Autowired
	CustomSuccessHandler customSuccessHandler;

	@Override
	protected void configure(HttpSecurity http) throws Exception {		
		http.authorizeRequests().antMatchers("/","/welcome").permitAll() // 根目录都可以访问
				.antMatchers("/welcome").hasAnyRole("USER", "ADMIN") /// welcome只有USER角色 和 ADMIN触角才能访问
				.antMatchers("/goods").hasAnyRole("USER", "ADMIN")/// getGoods只有USER角色 和 ADMIN角色 才能访问
				.antMatchers("/goods/add").hasAnyRole("ADMIN")/// addNewGoods只有ADMIN角色 才能访问
				.antMatchers("/goods/edit").hasAnyRole("ADMIN")/// addNewGoods只有ADMIN角色 才能访问
				.antMatchers("/goods/add").hasAnyRole("ADMIN")/// addNewGoods只有ADMIN角色 才能访问
				.anyRequest().authenticated()// 其它请求都需要身份验证
				.and().formLogin().loginPage("/login").successHandler(customSuccessHandler).permitAll() //登录页面      
				.and().logout().permitAll();
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.inMemoryAuthentication().withUser("user").password("{noop}user").authorities("ROLE_USER").and()
				.withUser("admin").password("{noop}888888").authorities("ROLE_USER", "ROLE_ADMIN");
	}
}
