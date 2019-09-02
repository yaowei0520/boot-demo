package com.yw.dao;

import com.yw.model.SysUserInfo;
import org.springframework.stereotype.Repository;

@Repository("sysUserInfoDao")
public interface SysUserInfoDao {

  int deleteByPrimaryKey(Integer id);

  int insert(SysUserInfo record);

  int insertSelective(SysUserInfo record);

  SysUserInfo selectByPrimaryKey(Integer id);

  int updateByPrimaryKeySelective(SysUserInfo record);

  int updateByPrimaryKey(SysUserInfo record);

  SysUserInfo getUserInfo(SysUserInfo userInfo);
}
