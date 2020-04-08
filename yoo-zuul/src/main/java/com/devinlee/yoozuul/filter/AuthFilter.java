package com.devinlee.yoozuul.filter;

import com.devinlee.yoozuul.constant.UrlConstant;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 权限验证
 */
@Component
public class AuthFilter extends ZuulFilter {

    /**
     * 请求在被路由之前执行
     * @return
     */
    @Override
    public String filterType() {

        return "pre";
    }

    /**
     * 通过返回的int值来定义过滤器的执行顺序，数字越小优先级越高。
     * @return
     */
    @Override
    public int filterOrder() {

        return 0;
    }

    /**
     * 返回一个Boolean值，判断该过滤器是否需要执行。返回true执行，返回false不执行。
     * @return
     */
    @Override
    public boolean shouldFilter() {

        //获取Zuul提供的请求上下文对象
        RequestContext requestContext = RequestContext.getCurrentContext();
        //从上下文中获取request对象
        HttpServletRequest httpServletRequest = requestContext.getRequest();

        if (httpServletRequest.getRequestURI().equalsIgnoreCase(UrlConstant.LOGIN_URI)||httpServletRequest.getRequestURI().equalsIgnoreCase(UrlConstant.REGISTER_URI)){
            //登录与注册接口无需校验
            return false;
        }
        return true;
    }

    /**
     * 登录校验逻辑
     * @return
     * @throws ZuulException
     */
    @Override
    public Object run() throws ZuulException {

        //获取Zuul提供的请求上下文对象
        RequestContext requestContext = RequestContext.getCurrentContext();
        //从上下文中获取request对象
        HttpServletRequest httpServletRequest = requestContext.getRequest();
        HttpServletResponse httpServletResponse = requestContext.getResponse();
        //从请求中获取token
        String token = httpServletRequest.getHeader("token");
        //判断
        if(token == null || "".equals(token.trim())){

            httpServletResponse.setContentType("text/html;charset=utf-8");
            try {
                httpServletResponse.sendError(403,"没有权限");
            } catch (IOException e) {
                e.printStackTrace();
            }
            //过滤该请求，不对其进行路由
            requestContext.setSendZuulResponse(false);
            ////返回401状态码。也可以考虑重定向到登录页。
            //requestContext.setResponseStatusCode(HttpStatus.UNAUTHORIZED.value());
        }
        return null;
    }
}
