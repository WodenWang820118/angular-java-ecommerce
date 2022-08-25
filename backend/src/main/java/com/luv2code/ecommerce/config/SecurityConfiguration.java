package com.luv2code.ecommerce.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.accept.ContentNegotiationStrategy;
import org.springframework.web.accept.HeaderContentNegotiationStrategy;

import com.okta.spring.boot.oauth.Okta;

@Configuration
public class SecurityConfiguration {
    // TODO: add Okta configuration
    // https://github.com/okta/okta-spring-boot
    @Bean
    protected SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
 
        // protect endpoint /api/orders
        // TODO: for now, provide the false antPatterns to allow all requests
        http.authorizeRequests()
                .antMatchers("api/orders/**")
                .authenticated();
                // .and()
                // .oauth2ResourceServer()
                // .jwt();
 
        // add CORS filters
        http.cors();
 
        // add content negotiation strategy
        http.setSharedObject(ContentNegotiationStrategy.class, new HeaderContentNegotiationStrategy());
 
        // force a non-empty response body for 401's to make the response more friendly
        Okta.configureResourceServer401ResponseBody(http);

        // diable CSRF for Okta
        http.csrf().disable();
 
        return http.build();
    }
}
