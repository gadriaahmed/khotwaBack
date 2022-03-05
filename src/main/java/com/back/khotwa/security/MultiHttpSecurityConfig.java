package com.back.khotwa.security;

import com.back.khotwa.security.api.ApiJWTAuthorizationFilter;
import com.back.khotwa.security.api.authenticationfilter.ApiJWTRecruiterAuthenticationFilter;
import com.back.khotwa.security.api.authenticationfilter.ApiJWTUserAuthenticationFilter;
import com.back.khotwa.security.authenticationProvider.RecruiterAuthenticationProvider;
import com.back.khotwa.security.authenticationProvider.UserAuthenticationProvider;
import com.back.khotwa.security.detailsService.CustomRecruiterDetailsService;
import com.back.khotwa.security.detailsService.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.servlet.http.HttpServletResponse;


@Configuration
@EnableWebSecurity
public  class MultiHttpSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private CustomUserDetailsService customUserDetailsService;

    @Autowired
    private CustomRecruiterDetailsService customRecruiterDetailsService;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

        @Override
        protected void configure(HttpSecurity http) throws Exception {
            // Disable CSRF (cross site request forgery)
            http.cors();
            http.csrf().disable();

            // No session will be created or used by spring security
            http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

            // Entry points
            http.authorizeRequests()//
                    .antMatchers(HttpMethod.OPTIONS, "/**/**/**/**/**").permitAll()
                    .antMatchers("/api/user/signup").permitAll()
                    .antMatchers("/api/recruiter/signup").permitAll()
               //    .antMatchers("/api/user/*").access("hasRole('USER')")
                    .and()
                    .authorizeRequests()
                    .anyRequest()
                    .authenticated()
                    .and()
                    .exceptionHandling()
                    .authenticationEntryPoint((req, rsp, e) -> rsp.sendError(HttpServletResponse.SC_UNAUTHORIZED))
                    .and()
                    .addFilter(new ApiJWTUserAuthenticationFilter(new UserAuthenticationProvider(),customUserDetailsService,bCryptPasswordEncoder))
                    .addFilter(new ApiJWTRecruiterAuthenticationFilter(new RecruiterAuthenticationProvider(),customRecruiterDetailsService,bCryptPasswordEncoder))
                    .addFilter(new ApiJWTAuthorizationFilter(authenticationManager()));

        }

        @Override
        public void configure(WebSecurity web) {
            // Allow swagger to be accessed without authentication
            web.ignoring().antMatchers("/v2/api-docs")//
                    .antMatchers("/swagger-resources/**")//
                    .antMatchers("/swagger-ui.html")//
                    .antMatchers("/configuration/**")//
                    .antMatchers("/webjars/**")//
                    .antMatchers("/public")
                    .antMatchers("/auth/refresh")//
                    .antMatchers("/ws/**")

                    // Un-secure H2 Database (for testing purposes, H2 console shouldn't be unprotected in production)
                    .and()
                    .ignoring()
                    .antMatchers("/h2-console/**/**");
        }
        // @

        /*  @Override
          protected void configure(AuthenticationManagerBuilder auth) throws Exception {
              auth
                      .userDetailsService(userDetailsService)
                      .passwordEncoder(bCryptPasswordEncoder);
          }*/
    }



