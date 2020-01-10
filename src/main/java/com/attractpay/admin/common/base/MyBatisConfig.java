package com.attractpay.admin.common.base;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Properties;

@Configuration
public class MyBatisConfig{

    /**
     * @description Add Interceptor fix bug for use BaseService. return ID rather than entity.
     * @author junji
     * @date 2020/1/8 15:47
     */
    @Bean
    public MybatisInterceptor sqlStatsInterceptor(){
        return new MybatisInterceptor();
    }
}
