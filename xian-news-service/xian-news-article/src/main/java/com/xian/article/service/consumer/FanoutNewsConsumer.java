//package com.xian.article.service.consumer;
//
//import org.springframework.amqp.rabbit.annotation.RabbitHandler;
//import org.springframework.amqp.rabbit.annotation.RabbitListener;
//import org.springframework.stereotype.Service;
//
///**
// * Fanout发布订阅模式
// * 邮件
// */
//@Service
//@RabbitListener(queues = {"news.fanout.queue"})
//public class FanoutNewsConsumer {
//
//    @RabbitHandler
//    public void receiveMessage(String msg) {
//        System.out.println("News->fanout 接收到了的订单信息是：" + msg);
//    }
//}
