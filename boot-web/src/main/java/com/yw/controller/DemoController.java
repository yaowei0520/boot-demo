package com.yw.controller;

import com.yw.common.Constants;
import com.yw.log.CommonLogType;
import com.yw.log.LoggerAdapter;
import com.yw.log.LoggerAdapterFactory;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class DemoController {

  private static final LoggerAdapter LOGGER = LoggerAdapterFactory.getLogger(CommonLogType.SYS_CONTROLLER.getLogName());

  @RequestMapping("index")
  public String demoIndex() {
    LOGGER.info("start page index");
    return "index";
  }

  @RequestMapping("login")
  public String demologin(HttpServletRequest request) {
    LOGGER.info("start page login");
    return "login";
  }

  public static void main(String[] args) {
    System.out.println(DigestUtils.md5DigestAsHex("123456".getBytes()));
  }

  @RequestMapping("toPage")
  public String toPage(HttpServletRequest request) {
    String url = request.getParameter("url");
    if (StringUtils.isNotBlank(url) && url.contains("login")) {
      request.getSession().removeAttribute(Constants.LOGIN_SESSION);
    }
    System.out.println(String.format("url:%s", url));
    return url;
  }
}
