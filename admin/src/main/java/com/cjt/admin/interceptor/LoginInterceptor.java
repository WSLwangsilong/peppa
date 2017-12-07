package com.cjt.admin.interceptor;

import com.cjt.common.util.TokenUtil;
import org.apache.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * token拦截器，验证token是否有效
 */
public class LoginInterceptor implements HandlerInterceptor {

  /**
   * 请求处理之前调用，返回false代表请求被拦截，不会向下执行
   */
  public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
          throws Exception {
    // 登陆判断逻辑，返回false代表拦截
    String token = request.getHeader("X-Token");
    Long userId = TokenUtil.parseToken(token, Long.class);
    // token无效，意味登录失效
    if (userId == null){
      // 需要重置usingWriter，可能由于springmvc已经处理过
      response.reset();
      response.setHeader("Content-Type", "application/json;charset=utf-8");
      response.setStatus(HttpStatus.UNAUTHORIZED.value());
      response.getWriter().write("登录失效，请去登录页面");
      return false;
    }
    return true;
  }

  /**
   * 请求处理后，且视图渲染前调用，用于modelAndView的操作
   */
  public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
                         ModelAndView modelAndView) throws Exception {

  }

  /**
   * 请求处理后，且视图渲染已经完成，用于资源清理工作
   */
  public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
          throws Exception {

  }

}
