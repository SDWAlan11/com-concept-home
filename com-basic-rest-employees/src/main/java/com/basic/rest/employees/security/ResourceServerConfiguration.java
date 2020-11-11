package com.basic.rest.employees.security;


import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.error.OAuth2AccessDeniedHandler;

@Configuration
@EnableResourceServer
public class ResourceServerConfiguration extends ResourceServerConfigurerAdapter {
 
 
    @Override
    public void configure(HttpSecurity http) throws Exception {
    	 http
         .headers()
             .frameOptions()
             .disable()
             .and()
         .authorizeRequests()
             .antMatchers("/","/home","/hello","/login").permitAll()
             .antMatchers("/v1/**").authenticated();
    }
 
}
