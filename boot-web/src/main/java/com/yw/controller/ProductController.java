package com.yw.controller;

import com.alibaba.fastjson.JSONObject;
import com.yw.log.CommonLogType;
import com.yw.log.LoggerAdapter;
import com.yw.log.LoggerAdapterFactory;
import com.yw.service.repository.ProductService;
import com.yw.service.repository.bean.ProductInfoServiceBean;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("product")
public class ProductController {

  private static final LoggerAdapter LOGGER = LoggerAdapterFactory.getLogger(CommonLogType.SYS_CONTROLLER.getLogName());

  @Autowired
  private ProductService productService;

  @RequestMapping(value = "queryList", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
  @ResponseBody
  public Object queryList(HttpServletRequest request) {
    Map<String, Object> result = new HashMap<>();
    int curr = Integer.valueOf(request.getParameter("page"));
    int limit = Integer.valueOf(request.getParameter("limit"));
    try {
      String productName = "%".concat(request.getParameter("productName")).concat("%");
      int total = productService.queryListCount(productName);
      result.put("count", total);
      if (total > 0) {
        List<ProductInfoServiceBean> list = productService.queryList(productName, curr, limit);
        result.put("data", list);
      }
      result.put("code", "0000");
      result.put("msg", "成功");
    } catch (Exception e) {
      result.put("code", "9999");
      result.put("msg", " ");
      LOGGER.error("ProductController.queryList Exception", e);
    }
    return result;
  }

  @RequestMapping("toProductEditPage")
  public String toProductEditPage(HttpServletRequest request) {
    String id = request.getParameter("id");
    try {
      ProductInfoServiceBean productInfo = productService.queryProductInfoById(Integer.valueOf(id));
      request.setAttribute("productInfo", productInfo);
    } catch (Exception e) {
      LOGGER.error("ProductController.toProductEditPage Exception", e);
    }
    return "order-edit";
  }

  @RequestMapping("productInfoAdd")
  @ResponseBody
  public Map<String, Object> productInfoAdd(HttpServletRequest request) {
    Map<String, Object> result = new HashMap<>();
    try {
      String productName = request.getParameter("productName");
      int productNameCount = productService.queryProductInfoByName(productName);
      if (productNameCount > 0) {
        result.put("code", "999");
        result.put("msg", "产品名称已经存在");
        return result;
      }
      ProductInfoServiceBean productInfo = new ProductInfoServiceBean();
      productInfo.setProductName(productName);
      productInfo.setDetail(request.getParameter("detail"));
      productInfo.setProductKey(UUID.randomUUID().toString().replaceAll("-", ""));
      Date current = new Date();
      productInfo.setCreateTime(current);
      productInfo.setUpdateTime(current);
      int count = productService.productInfoAdd(productInfo);
      if (count == 1) {
        result.put("code", "0000");
        result.put("msg", "增加成功");
        return result;
      }
    } catch (Exception e) {
      result.put("code", "999");
      result.put("msg", "增加失败");
      LOGGER.error("ProductController.toProductEditPage Exception", e);
    }
    return result;
  }

  @RequestMapping("productInfoEdit")
  @ResponseBody
  public Map<String, Object> productInfoEdit(HttpServletRequest request) {
    Map<String, Object> result = new HashMap<>();
    try {
      String field = request.getParameter("field");
      ProductInfoServiceBean productInfo = JSONObject.parseObject(field, ProductInfoServiceBean.class);
      productInfo.setUpdateTime(new Date());
      int count = productService.productInfoEidt(productInfo);
      if (count == 1) {
        result.put("code", "0000");
        result.put("msg", "保存成功");
        return result;
      }
    } catch (Exception e) {
      result.put("code", "999");
      result.put("msg", "保存失败");
      LOGGER.error("ProductController.toProductEditPage Exception", e);
    }
    return result;
  }
}
