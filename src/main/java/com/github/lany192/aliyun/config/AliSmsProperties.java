package com.github.lany192.aliyun.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties(prefix = "ali.sms")
public class AliSmsProperties {
    /**
     * 短信API产品名称（短信产品名固定，无需修改）
     */
    private String product = "Dysmsapi";

    /**
     * 短信API产品域名（接口地址固定，无需修改）
     */
    private String domain = "dysmsapi.aliyuncs.com";

    private String accessKeyId;

    private String accessSecret;
}
