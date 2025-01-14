package com.almis.gateway;

import com.almis.gateway.filters.ErrorFilter;
import com.almis.gateway.filters.PostFilter;
import com.almis.gateway.filters.PreFilter;
import com.almis.gateway.filters.RouteFilter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableZuulProxy
@EnableEurekaClient
@EnableHystrixDashboard
@Slf4j
public class ZuulApiGetWayRunner {

	public static void main(String[] args) {
		SpringApplication.run(ZuulApiGetWayRunner.class, args);
		log.info("Zuul server is running...");
	}

	@Bean
	public PreFilter preFilter() {
		return new PreFilter();
	}

	@Bean
	public PostFilter postFilter() {
		return new PostFilter();
	}

	@Bean
	public ErrorFilter errorFilter() {
		return new ErrorFilter();
	}

	@Bean
	public RouteFilter routeFilter() {
		return new RouteFilter();
	}
}
