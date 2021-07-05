package com.ahasan.item.auth;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

	@Override
	public void configure(WebSecurity web) {
		web.ignoring()
			.antMatchers("/v2/api-docs",
						"/swagger-resources/**",
						"/swagger-ui.html",
						"/webjars/**",
						"/swagger/**");
	}

  public void addCorsMappings(CorsRegistry registry) {
    registry.addMapping("/**");
  }
}
