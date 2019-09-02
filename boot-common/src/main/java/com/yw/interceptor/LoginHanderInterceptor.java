package com.yw.interceptor;

import com.yw.common.Constants;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

public class LoginHanderInterceptor implements HandlerInterceptor {

  private Logger logger = LoggerFactory.getLogger(getClass());

  @Override
  public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
    Object user = request.getSession().getAttribute(Constants.LOGIN_SESSION);
    logger.info("====> Interceptor:{}", request.getRequestURI());
    if(user == null || user.equals("") ){
      logger.info("====> unlogin");
      //未登陆，返回登陆页面
      request.getRequestDispatcher("login").forward(request,response);
      return false;
    }
    logger.info("====> logined");
    //已登陆，放行请求
    return true;
  }

  @Override
  public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
  }

  @Override
  public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
  }
}
