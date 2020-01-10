/**
 * 
 */
package com.attractpay.admin.common;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.http.HttpProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.filter.OrderedCharacterEncodingFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.CharacterEncodingFilter;

/**
 * @author Administrator
 *
 */

@Configuration
//开启配置属性的注入，通过@EnableConfigurationProperties声明
@EnableConfigurationProperties(HttpProperties.class)
//当CharacterEncodingFilter在类路径的条件下
@ConditionalOnClass(CharacterEncodingFilter.class)
//当spring.http.encoding=enabled的情况下，如果没有设置则默认为true，即条件符合
@ConditionalOnProperty(prefix = "spring.http.encoding", value = "enabled", matchIfMissing = true)
public class HttpEncodingAutoConfiguration {
 
    //注入HttpEncodingProperties属性
    @Autowired
    private HttpProperties httpProperties;
 
    //配置CharacterEncodingFilter这个Bean
    @Bean
    //容器中没有CharacterEncodingFilter这个Bean才会执行下面代码
    @ConditionalOnMissingBean(CharacterEncodingFilter.class)
    public CharacterEncodingFilter characterEncodingFilter() {
        CharacterEncodingFilter filter = new OrderedCharacterEncodingFilter();
        filter.setEncoding(this.httpProperties.getEncoding().getCharset().name());
        filter.setForceEncoding(this.httpProperties.getEncoding().isForce());
        return filter;
    }
 
}

