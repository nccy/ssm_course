package com.hnust.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * @author 长夜
 * @date 2023/4/15 21:25
 */
@Configuration
@ComponentScan({"com.hnust.service","com.hnust.entity"})
@Import({MysqlConfig.class,MybatisConfig.class})
public class SpringConfig {

}
