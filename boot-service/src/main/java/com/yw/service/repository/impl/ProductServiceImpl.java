package com.yw.service.repository.impl;

import com.yw.dao.ProductInfoDao;
import com.yw.log.CommonLogType;
import com.yw.log.LoggerAdapter;
import com.yw.log.LoggerAdapterFactory;
import com.yw.model.ProductInfo;
import com.yw.service.repository.ProductService;
import com.yw.service.repository.bean.ProductInfoServiceBean;
import com.yw.utils.BeanCopierUtils;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("productService")
public class ProductServiceImpl implements ProductService {

  private static final LoggerAdapter LOGGER = LoggerAdapterFactory.getLogger(CommonLogType.SYS_SERVICE.getLogName());

  @Autowired
  private ProductInfoDao productInfoDao;

  @Override
  public List<ProductInfoServiceBean> queryList(String productName, int curr, int limit) throws Exception {
    LOGGER.info("start ProductService.queryList");
    Map<String, Object> params = new HashMap<>();
    params.put("productName", productName);
    List<ProductInfo> list = productInfoDao.queryList(params, new RowBounds((curr-1)*limit, limit));
    List<ProductInfoServiceBean> productInfoServiceBeanList = BeanCopierUtils.copyList2List(list, ProductInfoServiceBean.class);
    LOGGER.info("ProductService.queryList resp[{}]", productInfoServiceBeanList.toString());
    return productInfoServiceBeanList;
  }

  @Override
  public Integer queryListCount(String productName) throws Exception {
    LOGGER.info("start ProductService.queryListCount");
    Map<String, Object> params = new HashMap<>();
    params.put("productName", productName);
    int count = productInfoDao.queryListCount(params);
    LOGGER.info("ProductService.queryListCount resp[{}]", count);
    return count;
  }

  @Override
  public ProductInfoServiceBean queryProductInfoById(int id) throws Exception {
    LOGGER.info("start ProductService.queryProductInfoById");
    ProductInfo productInfo = productInfoDao.queryProductInfoById(id);
    ProductInfoServiceBean productInfoServiceBean = BeanCopierUtils.copyOne2One(productInfo, ProductInfoServiceBean.class);
    LOGGER.info("ProductService.queryProductInfoById resp[{}]", productInfoServiceBean.toString());
    return productInfoServiceBean;
  }

  @Override
  public Integer productInfoAdd(ProductInfoServiceBean productInfoServiceBean) throws Exception {
    LOGGER.info("start ProductService.productInfoAdd, params:{}", productInfoServiceBean.toString());
    ProductInfo productInfo = BeanCopierUtils.copyOne2One(productInfoServiceBean, ProductInfo.class);
    int count = productInfoDao.insertSelective(productInfo);
    LOGGER.info("ProductService.productInfoAdd resp[{}]", count);
    return count;
  }

  @Override
  public Integer queryProductInfoByName(String productName) throws Exception {
    LOGGER.info("start ProductService.queryProductInfoByName, params:{}", productName);
    return productInfoDao.queryProductInfoByName(productName);
  }

  @Override
  public int productInfoEidt(ProductInfoServiceBean productInfoServiceBean) throws Exception {
    LOGGER.info("start ProductService.productInfoEidt, params:{}", productInfoServiceBean.toString());
    ProductInfo productInfo = BeanCopierUtils.copyOne2One(productInfoServiceBean, ProductInfo.class);
    int count = productInfoDao.updateByPrimaryKeySelective(productInfo);
    LOGGER.info("ProductService.productInfoEidt resp[{}]", count);
    return count;
  }
}
