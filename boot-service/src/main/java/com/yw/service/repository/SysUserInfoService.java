package com.yw.service.repository;

import com.yw.model.SysUserInfo;
import com.yw.service.repository.bean.SysUserInfoServiceBean;

public interface SysUserInfoService {

  SysUserInfoServiceBean getUserInfo(SysUserInfoServiceBean userInfo) throws Exception;

  int editUserInfo(SysUserInfoServiceBean userInfo) throws Exception;
}
