package com.my.spring.web;

import lombok.Getter;
import org.springframework.boot.autoconfigure.security.oauth2.resource.ResourceServerProperties;
import org.springframework.boot.context.properties.NestedConfigurationProperty;
import org.springframework.security.oauth2.client.token.grant.code.AuthorizationCodeResourceDetails;

@Getter
public class ClientResource {
    @NestedConfigurationProperty
    private ResourceServerProperties resource = new ResourceServerProperties();

    @NestedConfigurationProperty
    private AuthorizationCodeResourceDetails client = new AuthorizationCodeResourceDetails();
}
