package com.yw.service.repository;

import com.yw.service.repository.bean.ProductInfoServiceBean;
import java.util.List;

public interface ProductService {

  List<ProductInfoServiceBean> queryList(String productName, int curr, int limit) throws Exception;

  Integer queryListCount(String productName) throws Exception;

  ProductInfoServiceBean queryProductInfoById(int id) throws Exception;

  Integer productInfoAdd(ProductInfoServiceBean productInfo) throws Exception;

  Integer queryProductInfoByName(String productName) throws Exception;

  int productInfoEidt(ProductInfoServiceBean productInfo) throws Exception;
}
