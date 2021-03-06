package com.example.mall.config;

import com.example.mall.dto.QueueEnum;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.ExchangeBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.QueueBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 配置交换机、队列及队列与交换机的绑定关系。
 */
@Configuration
public class RabbitMQConfig {

  /**
   * 订单消息实际消费队列所绑定的交换机
   */
  @Bean
  DirectExchange orderDirect() {
    return ExchangeBuilder
        .directExchange(QueueEnum.QUEUE_ORDER_CANCEL.getExchange())
        .durable(true)
        .build();
  }

  /**
   * 订单延迟队列队列所绑定的交换机
   */
  @Bean
  DirectExchange orderTtlDirect() {
    return ExchangeBuilder
        .directExchange(QueueEnum.QUEUE_TTL_ORDER_CANCEL.getExchange())
        .durable(true)
        .build();
  }

  /**
   * 订单实际消费队列
   */
  @Bean
  public Queue orderQueue() {
    return new Queue(QueueEnum.QUEUE_ORDER_CANCEL.getName());
  }

  /**
   * 订单延迟队列（死信队列）
   */
  @Bean
  public Queue orderTtlQueue() {
    return QueueBuilder
        .durable(QueueEnum.QUEUE_TTL_ORDER_CANCEL.getName())
        .withArgument("x-dead-letter-exchange",
            QueueEnum.QUEUE_ORDER_CANCEL.getExchange())//到期后转发的交换机
        .withArgument("x-dead-letter-routing-key",
            QueueEnum.QUEUE_ORDER_CANCEL.getRouteKey())//到期后转发的路由键
        .build();
  }

  /**
   * 将订单队列绑定到交换机
   */
  @Bean
  Binding orderBinding(DirectExchange orderDirect, Queue orderQueue) {
    return BindingBuilder
        .bind(orderQueue)
        .to(orderDirect)
        .with(QueueEnum.QUEUE_ORDER_CANCEL.getRouteKey());
  }

  /**
   * 将订单延迟队列绑定到交换机
   */
  @Bean
  Binding orderTtlBinding(DirectExchange orderTtlDirect, Queue orderTtlQueue) {
    return BindingBuilder
        .bind(orderTtlQueue)
        .to(orderTtlDirect)
        .with(QueueEnum.QUEUE_TTL_ORDER_CANCEL.getRouteKey());
  }
}
