package com.yw.dao;

import com.yw.model.ProductInfo;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Repository;

@Repository("productInfoDao")
public interface ProductInfoDao {

  int deleteByPrimaryKey(Integer id);

  int insert(ProductInfo record);

  int insertSelective(ProductInfo record);

  ProductInfo selectByPrimaryKey(Integer id);

  int updateByPrimaryKeySelective(ProductInfo record);

  int updateByPrimaryKeyWithBLOBs(ProductInfo record);

  int updateByPrimaryKey(ProductInfo record);

  List<ProductInfo> queryList(Map<String, Object> params, RowBounds rowBounds);

  int queryListCount(Map<String, Object> params);

  ProductInfo queryProductInfoById(@Param("id") int id);

  int queryProductInfoByName(@Param("productName") String productName);
}
