package com.xian.wemedia.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * 服务降级配置类
 *  扫描降级代码类的包
 */
@Configuration
@ComponentScan("com.xian.apis.article.fallback")
public class InitConfig {
}