package com.example.mall.component;

import com.example.mall.dto.QueueEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 取消订单消息的发出者
 */
@Slf4j
@Component
public class CancelOrderSender {

  @Autowired
  private AmqpTemplate amqpTemplate;

  public void sendMessage(Long orderId, final long delayTimes) {
    // 给延迟队列发送消息
    amqpTemplate.convertAndSend(QueueEnum.QUEUE_TTL_ORDER_CANCEL.getExchange(),
        QueueEnum.QUEUE_TTL_ORDER_CANCEL.getRouteKey(), orderId,
        message -> {
          // 给消息设置延迟毫秒值
          message.getMessageProperties().setExpiration(String.valueOf(delayTimes));
          return message;
        });
    log.info("send delay message orderId:{}", orderId);
  }
}
