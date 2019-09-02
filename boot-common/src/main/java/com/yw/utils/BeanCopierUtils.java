package com.yw.utils;

import com.yw.code.base.CommonErrorCode;
import com.yw.exception.business.BusinessRuntimeException;
import com.yw.log.CommonLogType;
import com.yw.log.LoggerAdapter;
import com.yw.log.LoggerAdapterFactory;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.cglib.beans.BeanCopier;

public class BeanCopierUtils {
  private static final LoggerAdapter LOGGER = LoggerAdapterFactory.getLogger(CommonLogType.SYS_COMMON.getLogName());
  private static final Map<String, BeanCopier> BEANCOPIER_MAP = new HashMap<String, BeanCopier>();

  private BeanCopierUtils() {
  }

  /**
   * copy properties
   *
   * @param source
   * @param target
   */
  public static void copyProperties(Object source, Object target) {
    if (source == null || target == null) {
      return;
    }
    String beanKey = generateKey(source.getClass(), target.getClass());
    BeanCopier copier = BEANCOPIER_MAP.get(beanKey);
    if (copier == null) {
      copier = BeanCopier.create(source.getClass(), target.getClass(), false);
      BEANCOPIER_MAP.put(beanKey, copier);
    }
    copier.copy(source, target, null);
  }

  /**
   * 根据两个class名组装成key
   *
   * @param class1
   * @param class2
   * @return
   */
  private static String generateKey(Class<?> class1, Class<?> class2) {
    return new StringBuilder().append(class1.toString()).append(class2.toString()).toString();
  }

  /**
   * 拷贝对象
   *
   * @param source
   * @param target
   * @return
   * @throws BusinessRuntimeException
   */
  public static <T> T copyOne2One(Object source, Class<T> target) throws BusinessRuntimeException {
    T instance = null;
    try {
      instance = target.newInstance();
      copyProperties(source, instance);
    } catch (Exception e) {
      LOGGER.error("对象拷贝异常:source=[{}] target=[{}]", source, target);
      throw new BusinessRuntimeException(CommonErrorCode.COMMON_400, e);
    }
    return instance;
  }

  /**
   * 拷贝集合
   *
   * @param source
   * @param target
   * @return
   * @throws BusinessRuntimeException
   */
  public static <T> List<T> copyList2List(List<?> source, Class<T> target) throws BusinessRuntimeException {
    if (source.isEmpty()) {
      return new ArrayList<T>();
    }
    List<T> result = new ArrayList<T>();
    for (Object obj : source) {
      result.add(copyOne2One(obj, target));
    }
    return result;
  }

  /**
   *
   * @param source
   * @param target
   */
  public static void copyAttribute(Object source, Object target) {
    if (source == null || target == null)
      return;
    String key = new StringBuilder().append(source.getClass().toString()).append(target.getClass().toString())
        .toString();
    BeanCopier beanCopier = BEANCOPIER_MAP.get(key);
    if (beanCopier == null) {
      beanCopier = BeanCopier.create(source.getClass(), target.getClass(), false);
      BEANCOPIER_MAP.put(key, beanCopier);
    }
    beanCopier.copy(source, target, null);
  }
}
