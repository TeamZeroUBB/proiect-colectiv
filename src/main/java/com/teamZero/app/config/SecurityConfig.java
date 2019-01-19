package com.teamZero.app.config;

import com.teamZero.app.config.auth.AuthSuccessHandler;
import com.teamZero.app.config.auth.CorsFilter;
import com.teamZero.app.config.auth.CustomAuthenticationProvider;
import com.teamZero.app.config.auth.RestEntryPoint;
import com.teamZero.app.domain.user.UserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.session.SessionManagementFilter;

import javax.annotation.Resource;
import java.util.Base64;

@Configuration
@EnableWebSecurity
@ComponentScan("com.teamZero.app.config.auth")
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private CustomAuthenticationProvider authProvider;

    @Resource
    private RestEntryPoint restEntryPoint;

    @Resource
    private AuthSuccessHandler authSuccessHandler;


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authProvider);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http
                .addFilterBefore(new CorsFilter(), SessionManagementFilter.class)
                .csrf().disable()
                .exceptionHandling()
                    .authenticationEntryPoint(restEntryPoint)

               .and()
               .authorizeRequests()
                   .antMatchers("/user-service/**").hasAnyAuthority(UserRole.ROLE_USER.name(), UserRole.ROLE_ADMIN.name())
                   .antMatchers("/job-offer-service/**").hasAnyAuthority(UserRole.ROLE_USER.name(), UserRole.ROLE_ADMIN.name())
                   .antMatchers("/company-service/**").hasAnyAuthority(UserRole.ROLE_USER.name(), UserRole.ROLE_ADMIN.name())
                   .antMatchers("/admin-service/**").hasAnyAuthority(UserRole.ROLE_ADMIN.name())

                .and()
                .formLogin()
                    .loginProcessingUrl("/login")
                    .failureUrl("/login?error=true")
                    .passwordParameter("password")
                    .usernameParameter("username")
                    .successHandler(authSuccessHandler)

                .and()
                .logout();


    }

}
