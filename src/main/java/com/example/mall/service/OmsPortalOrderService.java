package com.example.mall.service;

import com.example.mall.common.api.CommonResult;
import com.example.mall.dto.OrderParam;
import org.springframework.transaction.annotation.Transactional;

/**
 * 前台订单管理
 */
public interface OmsPortalOrderService {

  /**
   * 根据提交信息生成订单
   */
  @Transactional
  CommonResult<Object> generateOrder(OrderParam orderParam);

  /**
   * 取消单个超时订单
   */
  @Transactional
  void cancelOrder(Long orderId);
}
