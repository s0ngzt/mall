package com.example.mall.service.impl;

import com.example.mall.common.api.CommonResult;
import com.example.mall.component.CancelOrderSender;
import com.example.mall.dto.OrderParam;
import com.example.mall.service.OmsPortalOrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 前台订单管理
 */
@Slf4j
@Service
public class OmsPortalOrderServiceImpl implements OmsPortalOrderService {

  @Autowired
  private CancelOrderSender cancelOrderSender;

  @Override
  public CommonResult<Object> generateOrder(OrderParam orderParam) {
    //todo 执行一系类下单操作，具体参考mall项目
    log.info("process generateOrder");
    //下单完成后开启一个延迟消息，用于当用户没有付款时取消订单（orderId应该在下单后生成）
    sendDelayMessageCancelOrder(11L);
    return CommonResult.success(null, "下单成功");
  }

  @Override
  public void cancelOrder(Long orderId) {
    //todo 执行一系类取消订单操作，具体参考mall项目
    log.info("process cancelOrder orderId:{}", orderId);
  }

  private void sendDelayMessageCancelOrder(Long orderId) {
    // 获取订单超时时间，假设为60分钟
    long delayTimes = 30 * 1000;
    // 发送延迟消息
    cancelOrderSender.sendMessage(orderId, delayTimes);
  }

}
