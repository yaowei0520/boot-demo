package com.yw.controller;

import com.alibaba.fastjson.JSONObject;
import com.yw.common.Constants;
import com.yw.log.CommonLogType;
import com.yw.log.LoggerAdapter;
import com.yw.log.LoggerAdapterFactory;
import com.yw.service.repository.SysUserInfoService;
import com.yw.service.repository.bean.SysUserInfoServiceBean;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class LoginController {

  private static final LoggerAdapter LOGGER = LoggerAdapterFactory.getLogger(CommonLogType.SYS_CONTROLLER.getLogName());

  @Autowired
  private SysUserInfoService sysUserInfoService;

  @RequestMapping("logining")
  @ResponseBody
  public Map<String, Object> demologin(HttpServletRequest request) {
    LOGGER.info("start page logining");
    Map<String, Object> resultMap = new HashMap<>();
    String field = request.getParameter("field");
    try {
      SysUserInfoServiceBean userInfo = JSONObject.parseObject(field, SysUserInfoServiceBean.class);
      userInfo.setPassword(DigestUtils.md5DigestAsHex(userInfo.getPassword().getBytes()));
      SysUserInfoServiceBean result = sysUserInfoService.getUserInfo(userInfo);
      if (result == null) {
        resultMap.put("code", "999");
        resultMap.put("msg", "用户名或者密码不正确");
        return resultMap;
      }
      HttpSession session = request.getSession();
      session.setAttribute(Constants.LOGIN_SESSION, result);
      session.setMaxInactiveInterval(15*60);
    } catch (Exception e) {
      LOGGER.error("LoginController.demologin Exception, param:{}", field, e);
      resultMap.put("code", "999");
      resultMap.put("msg", "登陆失败");
      return resultMap;
    }
    resultMap.put("code", "0000");
    resultMap.put("msg", "登陆成功");
    return resultMap;
  }

  @RequestMapping("pwdEdit")
  @ResponseBody
  public Map<String, Object> pwdEdit(HttpServletRequest request) {
    LOGGER.info("start page pwdEdit");
    Map<String, Object> resultMap = new HashMap<>();
    String field = request.getParameter("field");
    try {
      Map params = JSONObject.parseObject(field, Map.class);
      int id = Integer.valueOf((String) params.get("id"));
      String pwdold = (String) params.get("password1");
      String pwdnew = (String) params.get("password2");
      String userName = (String) params.get("userName");

      SysUserInfoServiceBean userInfo = new SysUserInfoServiceBean();
      userInfo.setPassword(DigestUtils.md5DigestAsHex(pwdold.getBytes()));
      userInfo.setUserName(userName);
      // 校验旧密码是否正确
      SysUserInfoServiceBean result = sysUserInfoService.getUserInfo(userInfo);
      if (result == null) {
        LOGGER.error("====> 旧密码不正确");
        resultMap.put("code", "999");
        resultMap.put("msg", "旧密码不正确");
        return resultMap;
      }

      userInfo.setId(id);
      userInfo.setPassword(DigestUtils.md5DigestAsHex(pwdnew.getBytes()));
      int count = sysUserInfoService.editUserInfo(userInfo);
      if (count < 1) {
        LOGGER.error("====> 修改失败");
        resultMap.put("code", "999");
        resultMap.put("msg", "修改失败");
        return resultMap;
      }
    } catch (Exception e) {
      LOGGER.error("LoginController.pwdEdit Exception", e);
      resultMap.put("code", "999");
      resultMap.put("msg", "修改失败");
      return resultMap;
    }
    resultMap.put("code", "0000");
    resultMap.put("msg", "修改成功");
    return resultMap;
  }

  public static void main(String[] args) {
    System.out.println(DigestUtils.md5DigestAsHex("123456".getBytes()));
  }
}
