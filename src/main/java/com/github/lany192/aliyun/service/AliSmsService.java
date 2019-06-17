package com.github.lany192.aliyun.service;

import com.aliyuncs.IAcsClient;
import com.aliyuncs.dysmsapi.model.v20170525.*;
import com.aliyuncs.exceptions.ClientException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class AliSmsService {
    private final IAcsClient client;

    @Autowired
    public AliSmsService(IAcsClient client) {
        this.client = client;
    }

    /**
     * 发送短信。详情https://help.aliyun.com/document_detail/55284.html?spm=a2c4g.11186623.6.647.71163520UlAX5d
     *
     * @param phone         待发送手机号
     * @param signName      短信签名-可在短信控制台中找到
     * @param templateCode  短信模板-可在短信控制台中找到
     * @param templateParam 模板中的变量替换JSON串,如模板内容为"亲爱的${name},您的验证码为${code}"时
     * @param outId         outId为提供给业务方扩展字段,最终在短信回执消息中将此值带回给调用者
     * @return SendSmsResponse
     * @throws ClientException
     */
    private SendSmsResponse sendSms(String phone, String signName,
                                    String templateCode, String templateParam, String outId)
            throws ClientException {
        SendSmsRequest request = new SendSmsRequest();
        //必填:待发送手机号
        request.setPhoneNumbers(phone);
        //必填:短信签名-可在短信控制台中找到
        request.setSignName(signName);
        //必填:短信模板-可在短信控制台中找到
        request.setTemplateCode(templateCode);
        //可选:模板中的变量替换JSON串,如模板内容为"亲爱的${name},您的验证码为${code}"时,此处的值为
        request.setTemplateParam(templateParam);
        //可选:outId为提供给业务方扩展字段,最终在短信回执消息中将此值带回给调用者
        request.setOutId(outId);
        //hint 此处可能会抛出异常，注意catch
        return client.getAcsResponse(request);
    }

    /**
     * 批量发送。详情https://help.aliyun.com/document_detail/66041.html
     *
     * @param phoneNumberJson   短信接收号码,JSON格式,批量上限为100个手机号码,批量调用相对于单条调用及时性稍有延迟,验证码类型的短信推荐使用单条调用的方式
     * @param signNameJson      短信签名,JSON格式
     * @param templateCode      短信模板ID
     * @param templateParamJson 短信模板变量替换JSON串,友情提示:如果JSON中需要带换行符,请参照标准的JSON协议。
     * @return SendBatchSmsResponse
     * @throws ClientException
     */
    public SendBatchSmsResponse sendBatchSms(String phoneNumberJson, String signNameJson,
                                             String templateCode, String templateParamJson) throws ClientException {
        SendBatchSmsRequest request = new SendBatchSmsRequest();
        request.setPhoneNumberJson(phoneNumberJson);
        request.setSignNameJson(signNameJson);
        //必填:短信模板-可在短信控制台中找到
        request.setTemplateCode(templateCode);
        request.setTemplateParamJson(templateParamJson);
        return client.getAcsResponse(request);
    }

    /**
     * 查询发送的短信。https://help.aliyun.com/document_detail/55289.html?spm=a2c4g.11186623.6.648.72a53144NTp88y
     *
     * @param bizId       可选-流水号
     * @param phone       必填-号码
     * @param sendDate    短信发送日期格式yyyyMMdd,支持最近30天记录查询
     * @param pageSize    必填-页大小
     * @param currentPage 必填-当前页码从1开始计数
     * @return QuerySendDetailsResponse
     * @throws ClientException
     */
    public QuerySendDetailsResponse querySendDetails(String bizId, String phone, String sendDate,
                                                     long pageSize, long currentPage)
            throws ClientException {
        //组装请求对象
        QuerySendDetailsRequest request = new QuerySendDetailsRequest();
        //必填-号码
        request.setPhoneNumber(phone);
        //可选-流水号
        request.setBizId(bizId);
        //必填-发送日期 支持30天内记录查询，格式yyyyMMdd
        request.setSendDate(sendDate);
        //必填-页大小
        request.setPageSize(pageSize);
        //必填-当前页码从1开始计数
        request.setCurrentPage(currentPage);
        return client.getAcsResponse(request);
    }
}