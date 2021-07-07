package com.almis.gateway.filters;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.http.HttpServletRequest;

/**
 * Zuul prefilter class. Used to tracing request
 */
@Slf4j
public class PreFilter extends ZuulFilter {

	public static final String HEADER_AUTHORIZATION = "Authorization";

	@Override
	public String filterType() {
		return "pre";
	}

	@Override
	public int filterOrder() {
		return 1;
	}

	@Override
	public boolean shouldFilter() {
		return true;
	}

	@Override
	public Object run() {
		RequestContext ctx = RequestContext.getCurrentContext();
		HttpServletRequest request = ctx.getRequest();

		log.info(
				"Request Method : " + request.getMethod() + " Request URL : " + request.getRequestURL().toString());
		/*
		 * Adding authorization header to zuul request header as zuul omits
		 * sensitive headers
		 */
		if (request.getHeader(HEADER_AUTHORIZATION) != null) {
			ctx.addZuulRequestHeader(HEADER_AUTHORIZATION, request.getHeader(HEADER_AUTHORIZATION));
		}
		return null;
	}

}