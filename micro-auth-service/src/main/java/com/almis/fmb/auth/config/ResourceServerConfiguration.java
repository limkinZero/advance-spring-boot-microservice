package com.almis.fmb.auth.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;

@EnableResourceServer
@Configuration
public class ResourceServerConfiguration extends ResourceServerConfigurerAdapter {

  @Value(value = "${security.oauth2.resource.id}")
  private String resourceId;

  private static final String SECURED_READ_SCOPE = "#oauth2.hasScope('READ')";
  private static final String SECURED_WRITE_SCOPE = "#oauth2.hasScope('WRITE')";
  private static final String SECURED_PATTERN = "/**";

  @Override
  public void configure(ResourceServerSecurityConfigurer resources) {
    resources.resourceId(resourceId);
  }

  @Override
  public void configure(HttpSecurity http) throws Exception {
    http.csrf().disable()
            .headers().frameOptions().disable() //H2 console
            .and().sessionManagement().disable()
            .authorizeRequests()
            .antMatchers("/actuator/**", "/h2-console/**").permitAll()
            .and().requestMatchers().antMatchers(SECURED_PATTERN)
            .and().authorizeRequests()
            .antMatchers(HttpMethod.POST, SECURED_PATTERN)
            .access(SECURED_WRITE_SCOPE)
            .anyRequest().access(SECURED_READ_SCOPE);
  }
}
