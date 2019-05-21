package com.lambda.essentialism.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.error.OAuth2AccessDeniedHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableResourceServer
public class ResourceServerConfig
  extends ResourceServerConfigurerAdapter {
  private static final String RESOURCE_ID = "resource_id";

  @Override
  public void configure(ResourceServerSecurityConfigurer resources) {
    resources.resourceId(RESOURCE_ID).stateless(false);
  }

  @Override
  public void configure(HttpSecurity http)
    throws
      Exception {// remember that there is also security set in the UserController!

    http.authorizeRequests().antMatchers(
      "/api/**",
      "/register",
      "/values",
      "/h2-console/**",
      "/v2/api-docs",
      "/swagger-resources",
      "/swagger-resources/**",
      "/configuration/ui",
      "/configuration/security",
      "/swagger-ui.html",
      "/webjars/**"
    ).permitAll().// hasAnyRole can be a list of roles as in "ADMIN", "DATA"
    antMatchers("/roles", "/actuator/**").hasAnyRole("ADMIN").antMatchers(
      "/**"
    ).authenticated().and().exceptionHandling().accessDeniedHandler(
      new OAuth2AccessDeniedHandler()
    );
  }

}

