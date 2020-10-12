package com.nashtech.training.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;

@Configuration
@EnableResourceServer
public class ResourceServerConfiguration extends ResourceServerConfigurerAdapter {
    private static final String RESOURCE_ID = "resource-springboot";

    @Override
    public void configure(ResourceServerSecurityConfigurer resources) {
        resources.resourceId(RESOURCE_ID);
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.cors()
            .and().requestMatchers().and().authorizeRequests()
            .antMatchers("/v3/api-docs/**",
                         "/swagger-resources/configuration/ui",
                         "/swagger-resources",
                         "/swagger-resources/configuration/security",
                         "/swagger-ui.html").permitAll()
            .antMatchers("/api/**").authenticated();
    }

}
