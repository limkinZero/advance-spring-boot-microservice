package com.almis.gateway.filters;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ErrorFilter extends ZuulFilter {

  @Override
  public String filterType() {
    return "error";
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
    RequestContext context = RequestContext.getCurrentContext();
    if (context.get("throwable") instanceof ZuulException) {
      ZuulException zuulException = (ZuulException) context.get("throwable");
      log.error("Inside Zuul Error Filter. Failure detected: " + zuulException.getMessage(), zuulException);
    }
    return null;
  }
}