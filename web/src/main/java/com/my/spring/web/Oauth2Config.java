package com.my.spring.web;


import com.my.spring.common.response.MockCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.oauth2.resource.UserInfoTokenServices;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.oauth2.client.OAuth2ClientContext;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.client.filter.OAuth2ClientAuthenticationProcessingFilter;
import org.springframework.security.oauth2.client.filter.OAuth2ClientContextFilter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableOAuth2Client;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.web.context.request.RequestContextListener;
import org.springframework.web.filter.CompositeFilter;

import javax.servlet.Filter;
import javax.servlet.http.Cookie;
import java.util.ArrayList;
import java.util.List;

@Configuration
@EnableOAuth2Client
public class Oauth2Config extends WebSecurityConfigurerAdapter {
    @Autowired
    private OAuth2ClientContext oauth2ClientContext;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .anyRequest()
                .permitAll()
                .and()
                .httpBasic()
                .disable()
                .addFilterBefore(ssoFilter(), BasicAuthenticationFilter.class);
    }

    private Filter ssoFilter() {
        CompositeFilter filter = new CompositeFilter();
        List<Filter> filters = new ArrayList<>();
        filters.add(ssoFilter(github(), "/login/github"));
        filter.setFilters(filters);
        return filter;
    }

    private Filter ssoFilter(ClientResource client, String path) {
        OAuth2ClientAuthenticationProcessingFilter githubFilter = new OAuth2ClientAuthenticationProcessingFilter(path);
        githubFilter.setAuthenticationSuccessHandler((request, response, authentication) -> {
            String sessionId = request.getSession().getId();
            MockCache.set(sessionId, authentication);
//            response.sendRedirect(response.encodeRedirectURL("http://localhost:8081?token=" + sessionId));
            response.sendRedirect("https://www.shownspace.cn?token=" + sessionId);
        });
        OAuth2RestTemplate githubTemplate = oAuth2RestTemplate();
        UserInfoTokenServices tokenServices = new UserInfoTokenServices(client.getResource().getUserInfoUri(), client.getClient().getClientId());
        githubFilter.setRestTemplate(githubTemplate);
        tokenServices.setRestTemplate(githubTemplate);
        githubFilter.setTokenServices(tokenServices);
        return githubFilter;
    }

    @Bean
    public OAuth2RestTemplate oAuth2RestTemplate() {
        return new OAuth2RestTemplate(github().getClient(), oauth2ClientContext);
    }

    @Bean
    @ConfigurationProperties("github")
    public ClientResource github() {
        return new ClientResource();
    }

    @Bean
    public FilterRegistrationBean oauth2ClientFilterRegistration(OAuth2ClientContextFilter filter) {
        FilterRegistrationBean registration = new FilterRegistrationBean();
        registration.setFilter(filter);
        registration.setOrder(-100);
        return registration;
    }

    @Bean
    public RequestContextListener requestContextListener() {
        return new RequestContextListener();
    }
}
