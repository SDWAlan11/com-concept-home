package com.auth.server.security;

import com.concept.test.data.entity.Employee;
import com.concept.test.data.repository.EmpleadoRepository;
import com.google.common.collect.ImmutableList;
import org.apache.catalina.filters.CorsFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User.UserBuilder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@Configuration
@EnableWebSecurity
public class OAuth2SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    private EmpleadoRepository empleadoRepository;

    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Bean
    public UserDetailsService getUserDetailsService() {
        UserDetailsService userDetailsService = new UserDetailsService() {

            @Override
            public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
                if ("admin".equals(username)){
                    UserBuilder builder = org.springframework.security.core.userdetails
                            .User.withUsername(username)
                            .password("$2a$10$6CW1agMzVzBhxDzK0PcxrO/cQcmN9h8ZriVEPy.6DJbVeyATG5mWe")
                            .roles("ADMIN");
                    return builder.build();
                }
                else {
                    Employee existingEmployee = empleadoRepository.findById(
                            Integer.valueOf(username)).orElseThrow(() -> new UsernameNotFoundException(username + " Not found"));

                    UserBuilder builder = org.springframework.security.core.userdetails
                            .User.withUsername(existingEmployee.getEmail())
                            .password(existingEmployee.getPassword())
                            .roles(existingEmployee.getJobTitle().name());
                    return builder.build();
                }
            }
        };
        return userDetailsService;
    }
}