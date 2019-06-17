# aliyun-sms-spring-boot-starter
阿里云短信集成aliyun-sms-spring-boot-starter

# 安装
 因为没有上传到中央仓库，使用需要将本项目clone到本地，运行安装到本地
 
 # 引用
 
    <!--阿里云短信服务，本地仓库-->
    <dependency>
        <groupId>com.github.lany192</groupId>
        <artifactId>aliyun-sms-spring-boot-starter</artifactId>
        <version>1.0.0</version>
    </dependency>
           
  然后在配置
  
    #阿里云短信服务==========================================================================
    ali.sms.access-key-id=xxxxxxxxx
    ali.sms.access-secret=xxxxxxxxxxxxxxxxxx
    
 ## 使用
 
    @Autowired
    AliSmsService aliSmsService;
    
    //发送短信
    aliSmsService.sendSms()
    //批量发送
    aliSmsService.sendBatchSms()
    //查询
    aliSmsService.querySendDetails()
