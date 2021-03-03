/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.serverspring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 *
 * @author march
 */
@SpringBootApplication  
public class Application {
    private ConfigurableApplicationContext ctx;
      
    public boolean startServer(){
        this.ctx = SpringApplication.run(Application.class); 
        return true;
    }
    public boolean stopServer(){
        this.ctx.close();
        return true;
    }

    @EnableWebSecurity
    @Configuration
    class WebSecurityConfig extends WebSecurityConfigurerAdapter {

        @Override
        protected void configure(HttpSecurity http) throws Exception {
            http.csrf().disable()
                .addFilterAfter(new JWTAuthorizationFilter(), UsernamePasswordAuthenticationFilter.class)
                .authorizeRequests()
                    //chiedi il token per le richieste premium;
                    .antMatchers(HttpMethod.POST, "/filterImagePremium").authenticated()
                    .antMatchers(HttpMethod.POST, "/**").permitAll()
                    .antMatchers(HttpMethod.GET, "/*").permitAll();
        }
    }
}
