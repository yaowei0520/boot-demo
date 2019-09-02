package com.yw.service.repository.impl;

import com.alibaba.fastjson.JSONObject;
import com.yw.dao.SysUserInfoDao;
import com.yw.log.CommonLogType;
import com.yw.log.LoggerAdapter;
import com.yw.log.LoggerAdapterFactory;
import com.yw.model.SysUserInfo;
import com.yw.service.repository.SysUserInfoService;
import com.yw.service.repository.bean.SysUserInfoServiceBean;
import com.yw.utils.BeanCopierUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("sysUserInfoService")
public class SysUserInfoServiceImpl implements SysUserInfoService {

  private static final LoggerAdapter LOGGER = LoggerAdapterFactory.getLogger(CommonLogType.SYS_SERVICE.getLogName());

  @Autowired
  private SysUserInfoDao sysUserInfoDao;

  @Override
  public SysUserInfoServiceBean getUserInfo(SysUserInfoServiceBean sysUserInfoServiceBean) throws Exception {
    LOGGER.info("start SysUserInfoService.getUserInfo, params:{}", sysUserInfoServiceBean.toString());
    SysUserInfo userInfo = BeanCopierUtils.copyOne2One(sysUserInfoServiceBean, SysUserInfo.class);
    SysUserInfo sysUserInfo = sysUserInfoDao.getUserInfo(userInfo);
    LOGGER.info("end SysUserInfoService.getUserInfo, resp:{}", JSONObject.toJSONString(sysUserInfo));
    return BeanCopierUtils.copyOne2One(sysUserInfo, SysUserInfoServiceBean.class);
  }

  @Override
  public int editUserInfo(SysUserInfoServiceBean sysUserInfoServiceBean) throws Exception {
    LOGGER.info("start SysUserInfoService.editUserInfo, params:{}", sysUserInfoServiceBean.toString());
    SysUserInfo userInfo = BeanCopierUtils.copyOne2One(sysUserInfoServiceBean, SysUserInfo.class);
    int count = sysUserInfoDao.updateByPrimaryKeySelective(userInfo);
    LOGGER.info("end SysUserInfoService.editUserInfo, resp:{}", count);
    return count;
  }
}
