package com.github.lany192.aliyun.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties(prefix = "aliyun.sms")
public class AliyunSmsProperties {
    /**
     * 阿里短信访问key
     */
    private String accessKeyId;
    /**
     * 阿里短信访问秘钥
     */
    private String accessSecret;
    /**
     * 数据中心节点，例如 cn-hangzhou
     */
    private String regionId = "cn-hangzhou";

    private String product = "Dysmsapi";

    private String endpoint = "dysmsapi.aliyuncs.com";

    private String connectTimeout = "10000";

    private String connectReadTimeout = "10000";
}
