package com.github.lany192.aliyun.config;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.utils.StringUtils;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties(AliyunSmsProperties.class)
public class AliyunSmsAutoConfig {

    @Bean
    @ConditionalOnMissingBean
    public IAcsClient aliyunSmsClient(AliyunSmsProperties properties) {
        if (StringUtils.isEmpty(properties.getAccessKeyId())) {
            throw new RuntimeException("配置阿里云短信服务参数");
        }
        DefaultProfile profile = DefaultProfile.getProfile(properties.getRegionId(), properties.getAccessKeyId(), properties.getAccessSecret());
        DefaultProfile.addEndpoint(properties.getRegionId(), properties.getProduct(), properties.getEndpoint());
        //可自助调整超时时间
        System.setProperty("sun.net.client.defaultConnectTimeout", properties.getConnectTimeout());
        System.setProperty("sun.net.client.defaultReadTimeout", properties.getConnectReadTimeout());
        return new DefaultAcsClient(profile);
    }
}
