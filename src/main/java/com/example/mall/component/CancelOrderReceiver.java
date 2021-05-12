package com.example.mall.component;

import com.example.mall.service.OmsPortalOrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 取消订单消息的处理者
 */
@Slf4j
@Component
@RabbitListener(queues = "mall.order.cancel")
public class CancelOrderReceiver {

  @Autowired
  private OmsPortalOrderService portalOrderService;

  @RabbitHandler
  public void handle(Long orderId) {
    log.info("receive delay message orderId:{}", orderId);
    portalOrderService.cancelOrder(orderId);
  }
}
