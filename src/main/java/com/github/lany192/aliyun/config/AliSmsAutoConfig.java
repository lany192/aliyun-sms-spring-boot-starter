package com.github.lany192.aliyun.config;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.profile.DefaultProfile;
import com.github.lany192.aliyun.service.AliSmsService;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties(AliSmsProperties.class)
public class AliSmsAutoConfig {

    @Bean
    @ConditionalOnMissingBean
    public IAcsClient aliSmsClient(AliSmsProperties properties) throws ClientException {
        DefaultProfile profile = DefaultProfile.getProfile("cn-hangzhou",
                properties.getAccessKeyId(), properties.getAccessSecret());
        DefaultProfile.addEndpoint("cn-hangzhou", "cn-hangzhou",
                properties.getProduct(), properties.getDomain());
        //可自助调整超时时间
        System.setProperty("sun.net.client.defaultConnectTimeout", "10000");
        System.setProperty("sun.net.client.defaultReadTimeout", "10000");
        return new DefaultAcsClient(profile);
    }

    @Bean
    public AliSmsService aliSmsService(IAcsClient client) {
        return new AliSmsService(client);
    }
}
