package com.xian.wemedia.config;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FanoutRabbitConfig {

    /**
     * 声明一个队列
     * @return
     */
    @Bean
    public Queue newsQueue() {
        // durable:是否持久化,默认是false,持久化队列：会被存储在磁盘上，当消息代理重启时仍然存在，暂存队列：当前连接有效
        // exclusive:默认也是false，只能被当前创建的连接使用，而且当连接关闭后队列即被删除。此参考优先级高于durable
        // autoDelete:是否自动删除，当没有生产者或者消费者使用此队列，该队列会自动删除。
        //   return new Queue("TestDirectQueue",true,true,false);
        //一般设置一下队列的持久化就好,其余两个就是默认false
        return new Queue("news.fanout.queue", true);
    }

    //Direct交换机 起名：TestDirectExchange
    @Bean
    public FanoutExchange fanoutNewsExchange() {
        //  return new DirectExchange("TestDirectExchange",true,true);
        return new FanoutExchange("fanout_news_exchange", true, false);
    }
    //绑定  将队列和交换机绑定, 并设置用于匹配键：TestDirectRouting
    @Bean
    public Binding bindingFanout1() {
        return BindingBuilder.bind(newsQueue()).to(fanoutNewsExchange());
    }

}
