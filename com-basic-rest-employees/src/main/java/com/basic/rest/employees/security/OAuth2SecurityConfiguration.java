package com.basic.rest.employees.security;

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
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableOAuth2Client;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.approval.ApprovalStore;
import org.springframework.security.oauth2.provider.approval.TokenApprovalStore;
import org.springframework.security.oauth2.provider.approval.TokenStoreUserApprovalHandler;
import org.springframework.security.oauth2.provider.request.DefaultOAuth2RequestFactory;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.InMemoryTokenStore;

import com.concept.test.data.entity.Employee;
import com.concept.test.data.repository.EmpleadoRepository;
 
@Configuration
@EnableWebSecurity
public class OAuth2SecurityConfiguration extends WebSecurityConfigurerAdapter {
 
    
    @Autowired
    private UserDetailsService userDetailsService;
    
    @Autowired
    private EmpleadoRepository empleadoRepository;
    
    @Autowired
    public void globalUserDetails(AuthenticationManagerBuilder auth) throws Exception {
    	auth.userDetailsService(userDetailsService);  	
    }



	@Override
    protected void configure(HttpSecurity http) throws Exception {
    	http
        .authorizeRequests()
            .antMatchers("/oauth/token").permitAll()
            .anyRequest().authenticated()
            .and()
        .httpBasic()
            .and()
        .csrf().disable();
    }
 
    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
 
     
    @Bean
    public UserDetailsService getUserDetailsService() {
    	UserDetailsService  userDetailsService = new UserDetailsService() {
			
			@Override
			public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
				Employee  existingEmployee = empleadoRepository.findById(
						Integer.valueOf(username)).orElseThrow(() -> new UsernameNotFoundException(username + " Not found"));
				
				UserBuilder  builder = org.springframework.security.core.userdetails
				.User.withUsername(existingEmployee.getEmail())
                .password(existingEmployee.getPassword())
                .roles(existingEmployee.getJobTitle().name());
				return builder.build();
				
			}
		};
		
		return userDetailsService;
    }
    
    @Bean
    public static BCryptPasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
    }
    
}